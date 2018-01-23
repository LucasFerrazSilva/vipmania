package br.com.vipmania.conf;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
public class JpaConfiguration {

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setUsername("VipMania");
		dataSource.setPassword("V1pM4n14");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/vipmania");

		Properties props = new Properties();
		
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.format_sql", "true");
		props.setProperty("hibernate.hbm2ddl.auto", "update");
		
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setJpaProperties(props);
		entityManagerFactoryBean.setPackagesToScan("br.com.vipmania.model");
		
		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory factory) {
		return new JpaTransactionManager(factory);
	}
	
}
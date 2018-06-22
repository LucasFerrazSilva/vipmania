package br.com.vipmania.conf;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.CharacterEncodingFilter;

import br.com.vipmania.dao.UserDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDAO userDao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/product/form/**").hasRole("ADMIN")
			.antMatchers("/cart").permitAll()
			.antMatchers("/cart/add/**").permitAll()	
			.antMatchers("/cart/remove/**").permitAll()
			.antMatchers("/cart/finalize").authenticated()
			.antMatchers("/cart/send-to-paypal").authenticated()
			.antMatchers("/calcular-frete/**").permitAll()			
			.antMatchers(POST, "/product/save").hasRole("ADMIN")
			.antMatchers(GET, "/product/list").hasRole("ADMIN")
			.antMatchers("/product/**").permitAll()
			.antMatchers("/order/list-all").hasRole("ADMIN")
			.antMatchers("/order/**").authenticated()
			.antMatchers("/address/list-all").hasRole("ADMIN")
			.antMatchers("/address/**").authenticated()
			.antMatchers("/image/**").permitAll()
			.antMatchers("/user/createAccount").permitAll()
			.antMatchers("/user/save").permitAll()
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
		
		CharacterEncodingFilter filter = new CharacterEncodingFilter(); 
		filter.setEncoding("UTF-8"); 
		filter.setForceEncoding(true); 
		http.addFilterBefore(filter, CsrfFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDao)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
	
}
package br.com.vipmania.conf;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import br.com.vipmania.dao.UserDAO;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	private UserDAO userDao;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/resources/**").permitAll()
			.antMatchers("/product/form").hasRole("ADMIN")
			.antMatchers("/cart").permitAll()
			.antMatchers("/cart/**").permitAll()	
			.antMatchers(POST, "/product/save").hasRole("ADMIN")
			.antMatchers(GET, "/product/list").hasRole("ADMIN")
			.antMatchers("/product/**").permitAll()
			.antMatchers("/qw56d1qw89d4qwd56as4d89qwd156asd48a97qw9e8sd65asw81a6s54q8w9").permitAll()			
			.antMatchers("/").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().loginPage("/login").permitAll()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDao)
			.passwordEncoder(new BCryptPasswordEncoder());
	}
	
}
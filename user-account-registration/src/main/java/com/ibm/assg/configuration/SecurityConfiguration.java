package com.ibm.assg.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/register").permitAll()
			.antMatchers("/confirm").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/home/**").permitAll()
			.antMatchers("/searchtra").permitAll()
			.antMatchers("/userlogin").permitAll()
			.antMatchers("/b2bb2c").permitAll()
			.antMatchers("/payment_angular").permitAll()
			.and().logout()
			   .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			   .logoutSuccessUrl("/")
			   .and().rememberMe();
			
	
	}
	
	
	

}
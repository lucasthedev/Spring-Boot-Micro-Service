package com.projuris.webservice.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	// authenticate configure
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

	}

	// authorization configure
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/listar").permitAll()
				.antMatchers(HttpMethod.GET, "/buscar/*").permitAll().antMatchers(HttpMethod.GET, "/buscarpornome")
				.permitAll().anyRequest().authenticated()
				.and().formLogin();

	}

	// static resources configuration
	@Override
	public void configure(WebSecurity web) throws Exception {

	}

}

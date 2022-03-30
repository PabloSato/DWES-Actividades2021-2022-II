package com.ite.springsecurity.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("SELECT username, password, enabled FROM Usuarios WHERE username=?")
				.authoritiesByUsernameQuery("SELECT u.username, p.descripcion FROM USUARIO_PERFILES up "
						+ "INNER JOIN Usuarios u ON u.username = up.username "
						+ "INNER JOIN Perfiles p ON p.id_perfil = up.id_perfil " + "WHERE u.username = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests().antMatchers("/css/**", "/img/**").permitAll()
				.antMatchers("/inicio", "/login", "/cliente/registro", "/logout").permitAll().antMatchers("/admin/**")
				.hasAnyAuthority("ROL_ADMON").antMatchers("/cliente/**").hasAnyAuthority("ROL_CLIENTE").anyRequest()
				.authenticated().and().formLogin().loginPage("/login").permitAll();
	}

}

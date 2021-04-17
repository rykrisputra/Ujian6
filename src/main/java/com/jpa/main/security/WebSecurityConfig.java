package com.jpa.main.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.jpa.main.security.CustomUserDetailServices;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	 private DataSource dataSource;
    
    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailServices();
    }
     
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
     
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());  
        return authProvider;
    }
 
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
     /*   http.authorizeRequests()
            .antMatchers("/mahasiswa").authenticated()
            .anyRequest().permitAll()
            .and()
            .formLogin().loginPage("/login")
            .defaultSuccessUrl("/mahasiswa/view")
             .permitAll()
            .and()
            .logout().logoutSuccessUrl("/").permitAll();
            */
    	
    	http.authorizeRequests()
    	.antMatchers("/laporan/view").access("hasRole('ROLE_ADMIN')")
    	.antMatchers("/laporan/add").access("hasRole('ROLE_USER')")
    	.and()
    	.formLogin().loginPage("/login")
    	.defaultSuccessUrl("/dashboard").permitAll()
    	.and()
    	.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");

	
}
}

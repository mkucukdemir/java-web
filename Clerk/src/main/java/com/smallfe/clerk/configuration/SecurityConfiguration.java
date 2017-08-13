/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smallfe.clerk.configuration;

import com.smallfe.clerk.components.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Responsible for all the security (protecting the application URLs, validating
 * submitted username and passwords, redirecting to the log in form, etc) within
 * our application.
 * @author mkucukdemir
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private CustomAuthenticationProvider authenticationProvider;
    
    /**
     * Responsible for processing any authentication request
     * Used in-memory authentication while you are free to choose from JDBC, LDAP and other authentications
     * In memory authentication
     * LDAP authentication
     * JDBC based authentication
     * Adding UserDetailsService
     * Adding AuthenticationProvider
     * @param auth
     * @throws Exception 
     */
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }
    
    /**
     * Configure web based security for specific http requests
     * By default it will be applied to all requests, but can be restricted
     * using requestMatcher(RequestMatcher)/antMathchers or other similar methods
     * @param http
     * @throws Exception 
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * URL’s ‘/’ & ‘/home’ are not secured, anyone can access them. URL
         * ‘/admin/**’ can only be accessed by someone who have ADMIN role. URL
         * ‘/db/**’ can only be accessed by someone who have both ADMIN and DBA roles
         */
        http.authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .antMatchers("/ajax/**").permitAll()
            .antMatchers("/admin/**").access("hasRole('ADMIN')")
            .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
            .and().formLogin()
            // Catch all 403 [http access denied] exceptions
            .and().exceptionHandling().accessDeniedPage("/Access_Denied");
    }
    
}
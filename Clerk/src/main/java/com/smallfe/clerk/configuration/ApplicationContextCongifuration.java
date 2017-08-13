/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smallfe.clerk.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Write short comment related with created class
 * @author mkucukdemir
 */
@Configuration
@ComponentScan("com.smallfe.clerk")
@EnableTransactionManagement
@EnableWebMvc
public class ApplicationContextCongifuration extends WebMvcConfigurerAdapter {
    
    @Autowired
    Environment environment;
    
    @Bean(name = "viewResolver")
    public InternalResourceViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();
        source.setBasenames("/WEB-INF/classes/messages");
        source.setUseCodeAsDefaultMessage(true);
        return source;
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }
    
}

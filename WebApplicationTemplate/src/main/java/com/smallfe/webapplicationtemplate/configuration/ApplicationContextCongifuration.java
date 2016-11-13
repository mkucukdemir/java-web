/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smallfe.webapplicationtemplate.configuration;

import com.smallfe.rolemanagementwsc.service.RoleManagementService;
import com.smallfe.userauthenticationwsc.UserAuthenticationService;
import com.smallfe.webapplicationtemplate.dao.ApplicationDao;
import com.smallfe.webapplicationtemplate.dao.ApplicationDaoImpl;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
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
@ComponentScan("com.smallfe.webapplicationtemplate")
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("/WEB-INF/properties/jdbc.properties")
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
    
    @Bean(name = "dataSource")
    public DataSource getDataSource() {
    	DriverManagerDataSource dataSource = new DriverManagerDataSource();
    	dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
    	dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
    	dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
    	dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
    	
    	return dataSource;
    }
    
    private Properties getHibernateProperties() {
    	Properties properties = new Properties();
    	properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        return properties; 
    }
    
    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
    	sessionBuilder.addProperties(getHibernateProperties());
        sessionBuilder.scanPackages("com.smallfe.webapplicationtemplate.model");
    	return sessionBuilder.buildSessionFactory();
    }
    
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
    
    @Autowired
    @Bean(name = "applicationDao")
    public ApplicationDao getApplicationDao(SessionFactory sessionFactory) {
    	return new ApplicationDaoImpl(sessionFactory);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }
    
    @Autowired
    @Bean(name = "userAuthenticationService")
    public UserAuthenticationService getUserAuthenticationService() {
    	return new UserAuthenticationService();
    }
    
    @Autowired
    @Bean(name = "roleManagementService")
    public RoleManagementService getRoleManagementService() {
    	return new RoleManagementService();
    }
}

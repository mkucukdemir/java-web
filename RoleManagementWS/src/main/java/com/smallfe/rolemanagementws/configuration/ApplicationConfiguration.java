package com.smallfe.rolemanagementws.configuration;

import com.smallfe.rolemanagementws.dao.RoleDao;
import com.smallfe.rolemanagementws.service.RoleService;
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
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.BeanNameViewResolver;

/**
 * Write short comment on created class
 * Created on 24.Mar.2016
 * @author mkucukdemir
 */
@Configuration
@ComponentScan("com.smallfe.rolemanagementws")
@EnableTransactionManagement
@EnableWebMvc
@PropertySource("/WEB-INF/properties/jdbc.properties")
public class ApplicationConfiguration {
    
    @Autowired
    Environment environment;
    
    @Bean
    public View jsonTemplate() {
        MappingJackson2JsonView view = new MappingJackson2JsonView();
        view.setPrettyPrint(true);
        return view;
    }
     
    @Bean
    public ViewResolver viewResolver() {
        return new BeanNameViewResolver();
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
        sessionBuilder.scanPackages("com.smallfe.rolemanagementws.model");
    	return sessionBuilder.buildSessionFactory();
    }
    
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
        return transactionManager;
    }
    
    @Autowired
    @Bean(name = "roleDao")
    public RoleDao getRoleDao(SessionFactory sessionFactory) {
    	return new RoleDao(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "roleService")
    public RoleService getRoleService() {
    	return new RoleService();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.userauthenticationws;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

/**
 *
 * @author mkucukdemir
 */
@WebService(serviceName = "UserAuthenticationWS")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class UserAuthenticationWS {
    
    @Resource
    private WebServiceContext context;
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "isAuthorized")
    public Boolean isAuthorized(@WebParam(name = "username") String username, @WebParam(name = "password") String password) {
        try {
            MessageContext mc = context.getMessageContext();
            ServletContext servletContext = (ServletContext) mc.get(MessageContext.SERVLET_CONTEXT);
            
            InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/properties/jdbc.properties");

            Properties properties = new Properties();
            properties.load(inputStream);
            
            Class.forName(properties.getProperty("jdbc.driverClassName"));
            String URL = properties.getProperty("jdbc.url");
            String USER = properties.getProperty("jdbc.username");
            String PASS = properties.getProperty("jdbc.password");
            Connection connection = DriverManager.getConnection(URL, USER, PASS);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT 1 RESULT FROM JWAS.APP_USER WHERE USERNAME='"+username+"' AND PASSWORD_DIGEST='"+password+"'");
            try {
                while(resultSet.next()){
                    int supplierID = resultSet.getInt("RESULT");
                    connection.close();
                    return supplierID==1;
                }
            } catch (Exception ex) {
                Logger.getLogger(UserAuthenticationWS.class.getName()).log(Level.SEVERE, null, ex);
                connection.close();
            }
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(UserAuthenticationWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}

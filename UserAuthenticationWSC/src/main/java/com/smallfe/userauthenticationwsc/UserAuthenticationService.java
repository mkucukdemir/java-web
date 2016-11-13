/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smallfe.userauthenticationwsc;

import com.smallfe.userauthenticationws.UserAuthenticationWS;
import com.smallfe.userauthenticationws.UserAuthenticationWS_Service;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Write short comment related with created class
 * @author mkucukdemir
 */
public class UserAuthenticationService {
    
    public UserAuthenticationService() {
    }
    
    public boolean isAuthorized(java.lang.String username, java.lang.String password) {
        UserAuthenticationWS_Service service = new UserAuthenticationWS_Service();
        UserAuthenticationWS port = service.getUserAuthenticationWSPort();
        try {
            return port.isAuthorized(username, password);
        } catch (Exception ex) {
            Logger.getLogger(UserAuthenticationService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}

package com.smallfe.userauthenticationwsc;

import com.smallfe.userauthenticationws.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Write short comment on created class
 * Created on 20.Mar.2016
 * @author Mehmet
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

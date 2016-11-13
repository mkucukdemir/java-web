/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smallfe.webapplicationtemplate.components;

import com.smallfe.rolemanagementwsc.model.Role;
import com.smallfe.rolemanagementwsc.service.RoleManagementService;
import com.smallfe.userauthenticationwsc.UserAuthenticationService;
import com.smallfe.webapplicationtemplate.service.DataService;
import com.smallfe.webapplicationtemplate.util.DigestUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

/**
 * Write short comment related with created class
 * @author mkucukdemir
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    
    @Autowired
    private RoleManagementService roleManagementService;
    @Autowired
    private UserAuthenticationService userAuthenticationService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        Boolean isAuthorized = userAuthenticationService.isAuthorized(username, DigestUtil.getSHA256(password));
        
        if (!isAuthorized) {
            throw new BadCredentialsException("Username not found.");
        }
        
        List<Role> userRoles = roleManagementService.getUserRoles(username);
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        for(int i=0;i<userRoles.size();i++){
            switch(userRoles.get(i).getId()){
                case 1:
                    setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
                    break;
                case 2:
                    setAuths.add(new SimpleGrantedAuthority("ROLE_COORDINATOR"));
                    break;
                case 3:
                    setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
                    break;
                case 4:
                    setAuths.add(new SimpleGrantedAuthority("ROLE_SUPERVISOR"));
                    break;
            }
        }
     
        return new UsernamePasswordAuthenticationToken(username, password, setAuths);
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
    
}

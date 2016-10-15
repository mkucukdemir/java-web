/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.rolemanagementws;

import com.smallfe.rolemanagementws.service.RoleService;
import com.smallfe.rolemanagementws.model.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mkucukdemir
 */
@RestController
public class RoleManagementWS {

    @Autowired
    RoleService roleService;
    
    @RequestMapping(value = "/{username}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Role>> getUserRoles(@PathVariable String username) {
        return new ResponseEntity<List<Role>>(roleService.getUserRoles(username), HttpStatus.OK);
    }

}

package com.smallfe.rolemanagementws.service;

import com.smallfe.rolemanagementws.dao.RoleDao;
import com.smallfe.rolemanagementws.model.Role;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * Write short comment on created class
 * Created on 24.Mar.2016
 * @author mkucukdemir
 */
@Service
@Transactional
public class RoleService {
    
    @Autowired
    RoleDao roleDao;

    public RoleService() {
        System.out.println("***Default Constructor: Role Service***");
    }

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
        System.out.println("***Copy Constructor: Role Service***");
    }
    
    public List<Role> getUserRoles(String username) {
        try {
            return roleDao.getUserRoles(username);
        } catch (Exception e) {
            System.out.println("Role Dao exception : " + e.getMessage());
            return null;
        }
    }
    
}

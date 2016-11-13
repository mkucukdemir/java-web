package com.smallfe.rolemanagementws.dao;

import com.smallfe.rolemanagementws.model.Role;
import com.smallfe.rolemanagementws.model.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Write short comment on created class
 * Created on 20.Mar.2016
 * @author mkucukdemir
 */
@Repository
public class RoleDao {
    
    @Autowired
    protected SessionFactory sessionFactory;
 
    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public RoleDao() {
    }
    
    public RoleDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Role> getUserRoles(String username) {
        Query query = (Query) this.getSession().createQuery("from User where username=:username");
        query.setParameter("username", username);
        query.setFirstResult(0);
        query.setMaxResults(1);
        
        try {
            List<Role> roles = ((User)query.list().get(0)).getRoles();
            return roles;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
}

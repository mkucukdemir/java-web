/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smallfe.webapplicationtemplate.dao;

import com.smallfe.webapplicationtemplate.model.User;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Write short comment related with created class
 * @author mkucukdemir
 */
@Repository
public class ApplicationDaoImpl extends AbstractDao implements ApplicationDao {
    
    public ApplicationDaoImpl() {
    }

    public ApplicationDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
     
    @Override
    public void testConnection() {
        String sql = "SELECT 1 FROM DUAL";
        SQLQuery query = this.getSession().createSQLQuery(sql);
        query.list();
    }

    @Override
    public User getKisiByUsername(String username) {
        Query query = (Query) this.getSession().createQuery("from Kisi where kullaniciAdi=:kullaniciAdi");
        query.setParameter("kullaniciAdi", username);
        query.setFirstResult(0);
        query.setMaxResults(1);
        try {
            return ((List<User>) query.list()).get(0);
        } catch (Exception e) {
            return null;
        }
    }
    
    @Override
    public List<String> getUsernameList() {
        Query query = (Query) this.getSession().createQuery("select kullaniciAdi from Kisi");
        query.setMaxResults(1000);
        try {
            return query.list();
        } catch (Exception e) {
            return null;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smallfe.webapplicationtemplate.service;

import com.smallfe.webapplicationtemplate.dao.ApplicationDao;
import com.smallfe.webapplicationtemplate.model.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Write short comment related with created class
 * @author mkucukdemir
 */
@Service
@Transactional
public class DataServiceImpl implements DataService {
    
    @Autowired
    ApplicationDao applicationDao;

    @Override
    public void testConnection() {
        applicationDao.testConnection();
    }

    @Override
    public User getKisiByUsername(String username) {
        return applicationDao.getKisiByUsername(username);
    }
    
    @Override
    public List<String> getUsernameList() {
        return applicationDao.getUsernameList();
    }
}

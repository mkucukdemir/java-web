/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.webapplicationtemplate.dao;

import com.smallfe.webapplicationtemplate.model.User;
import java.util.List;

/**
 * Write short comment related with created class
 * @author mkucukdemir
 */
public interface ApplicationDao {

    public void testConnection();

    public User getKisiByUsername(String username);

    public List<String> getUsernameList();

}

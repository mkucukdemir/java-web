/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor;

import com.smallfe.accountactivitiesmonitor.dto.AccountActivityFile;
import com.smallfe.accountactivitiesmonitor.dto.Category;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author mehmet.kucukdemir
 */
public class SessionDataContainer {
    
    private static SessionDataContainer sessionDataContainerInstance = null;
    private Map<String,SessionData> sessionData;

    private SessionDataContainer() {
        this.sessionData = new HashMap<>();
    }
    
    public static SessionDataContainer getInstance() {
        if(sessionDataContainerInstance==null){
            sessionDataContainerInstance = new SessionDataContainer();
        }
        return sessionDataContainerInstance;
    }

    public Map<String, SessionData> getSessionData() {
        return sessionData;
    }

    public void setSessionData(Map<String, SessionData> sessionData) {
        this.sessionData = sessionData;
    }

    public void putIfNotExists(String sessionId, AccountActivityFile file) {
        if(!sessionData.containsKey(sessionId))
            sessionData.put(sessionId, new SessionData());
        
        sessionData.get(sessionId).addOrModify(file); 
    }

    public boolean isAccountActivityFileExists(String sessionId,String filePath) {
        if(!sessionData.containsKey(sessionId))
            return false;
        else
            return sessionData.get(sessionId).containsAccountActivityFiles(filePath);
    }
    
    public int getTitleRow(String sessionId,String filePath) {
        return sessionData.get(sessionId).getAccountActivityFiles().get(filePath).getTitleRowIndex();
    }
    
    public int getNumOfColumns(String sessionId,String filePath) {
        return sessionData.get(sessionId).getAccountActivityFiles().get(filePath).getNumOfColumns();
    }

    public void putIfNotExists(String sessionId, Category category) {
        if(!sessionData.get(sessionId).getCategories().containsKey(category.getName()))
            sessionData.get(sessionId).getCategories().put(sessionId, category);
        
        sessionData.get(sessionId).addOrModify(category); 
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor;

import com.smallfe.accountactivitiesmonitor.dto.AccountActivity;
import com.smallfe.accountactivitiesmonitor.dto.AccountActivityFile;
import com.smallfe.accountactivitiesmonitor.dto.Category;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mehmet.kucukdemir
 */
public class SessionData {
    
    private Map<String,AccountActivityFile> accountActivityFiles;
    private List<AccountActivity> accountActivities;
    private Map<Date,List<AccountActivity>> accountActivitiesByDate;
    private Map<String,Category> categories;

    public SessionData() {
        this.accountActivityFiles = new HashMap<>();
        this.accountActivities = new ArrayList<>();
        this.accountActivitiesByDate = new HashMap<>();
        this.categories = new HashMap<>();
    }

    /**
     * @return the accountActivityFiles
     */
    public Map<String,AccountActivityFile> getAccountActivityFiles() {
        return accountActivityFiles;
    }

    /**
     * @param accountActivityFiles the accountActivityFiles to set
     */
    public void setAccountActivityFiles(Map<String,AccountActivityFile> accountActivityFiles) {
        this.accountActivityFiles = accountActivityFiles;
    }

    /**
     * @return the accountActivities
     */
    public List<AccountActivity> getAccountActivities() {
        return accountActivities;
    }

    /**
     * @param accountActivities the accountActivities to set
     */
    public void setAccountActivities(List<AccountActivity> accountActivities) {
        this.accountActivities = accountActivities;
    }

    /**
     * @return the accountActivitiesByDate
     */
    public Map<Date,List<AccountActivity>> getAccountActivitiesByDate() {
        return accountActivitiesByDate;
    }

    /**
     * @param accountActivitiesByDate the accountActivitiesByDate to set
     */
    public void setAccountActivitiesByDate(Map<Date,List<AccountActivity>> accountActivitiesByDate) {
        this.accountActivitiesByDate = accountActivitiesByDate;
    }

    /**
     * @return the categories
     */
    public Map<String,Category> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(Map<String,Category> categories) {
        this.categories = categories;
    }

    void addOrModify(AccountActivityFile file) {
        if(this.accountActivityFiles.containsKey(file.getPath())){
            this.accountActivityFiles.remove(file.getPath());
        }
        this.accountActivityFiles.put(file.getPath(), file);
    }

    boolean containsAccountActivityFiles(String filePath) {
        return this.accountActivityFiles.containsKey(filePath);
    }

    void addOrModify(Category category) {
        if(this.categories.containsKey(category.getName())){
            this.categories.remove(category.getName());
        }
        this.categories.put(category.getName(), category);
    }
        
}

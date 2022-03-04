/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.dto;

import com.google.gson.annotations.Expose;
import java.util.List;
/**
 *
 * @author mehmet.kucukdemir
 */
public class Category {
    
    private String name;
    private Criteria details;
    @Expose(serialize = false, deserialize = true)
    private List<Integer> accountActivityIndexes;

    public Category() {
    }
    
    public Category(String name) {
        this.name = name;
    }
    
    public Category(String name, Criteria details) {
        this.name = name;
        this.details = details;
    }

    public Category(String name, Criteria details, List<Integer> activities) {
        this.name = name;
        this.details = details;
        this.accountActivityIndexes = activities;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the details
     */
    public Criteria getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(Criteria details) {
        this.details = details;
    }

    /**
     * @return the accountActivityIndexes
     */
    public List<Integer> getAccountActivityIndexes() {
        return accountActivityIndexes;
    }

    /**
     * @param accountActivityIndexes the accountActivityIndexes to set
     */
    public void setAccountActivityIndexes(List<Integer> accountActivityIndexes) {
        this.accountActivityIndexes = accountActivityIndexes;
    }
    
}

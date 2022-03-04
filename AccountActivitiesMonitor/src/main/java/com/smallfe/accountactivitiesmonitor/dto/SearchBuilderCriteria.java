/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.dto;

import java.util.List;

/**
 *
 * @author mehmet.kucukdemir
 */
public class SearchBuilderCriteria implements Criteria{
    
    private String condition;
    private String data;
    private String origData;
    private String type;
    private List<String> value;

    public SearchBuilderCriteria() {
    }

    public SearchBuilderCriteria(String condition, String data, String origData, String type, List<String> value) {
        this.condition = condition;
        this.data = data;
        this.origData = origData;
        this.type = type;
        this.value = value;
    }
    
    /**
     * @return the condition
     */
    public String getCondition() {
        return condition;
    }

    /**
     * @param condition the condition to set
     */
    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the origData
     */
    public String getOrigData() {
        return origData;
    }

    /**
     * @param origData the origData to set
     */
    public void setOrigData(String origData) {
        this.origData = origData;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the value
     */
    public List<String> getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(List<String> value) {
        this.value = value;
    }
    
}

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
public class SearchBuilderDetails implements Criteria {
    
    private List<Criteria> criteria;
    private String logic;

    /**
     * @return the criteria
     */
    public List<Criteria> getCriteria() {
        return criteria;
    }

    /**
     * @param criteria the criteria to set
     */
    public void setCriteria(List<Criteria> criteria) {
        this.criteria = criteria;
    }

    /**
     * @return the logic
     */
    public String getLogic() {
        return logic;
    }

    /**
     * @param logic the logic to set
     */
    public void setLogic(String logic) {
        this.logic = logic;
    }
    
}

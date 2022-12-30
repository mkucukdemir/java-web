/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.apexchart;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mehmet.kucukdemir
 */
public class Serie {
    
    private String name;
    private List<List<Long>> data;

    public Serie(String name) {
        this.name = name;
        this.data = new ArrayList<>();
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
     * @return the data
     */
    public List<List<Long>> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<List<Long>> data) {
        this.data = data;
    }

    public void push(List<Long> data) {
        this.data.add(data);
    }

    public Long lastDataTime() {
        return this.data.get(this.data.size()-1).get(0);
    }

    public void accumulateOnLast(Long payment) {
        Long temp = this.data.get(this.data.size()-1).get(1);
        this.data.get(this.data.size()-1).remove(1);
        this.data.get(this.data.size()-1).add(payment + temp);
    }

    public Long getTimeOfLastDataEntry() {
        return this.data.get(this.data.size()-1).get(0);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.dto;

/**
 *
 * @author mehmet.kucukdemir
 */
public class AccountActivityFile {
    
    private String path;
    private int titleRow;
    private int numOfColumns;
    private AccountActivityTitleMapping titleMapping;

    /**
     * @return the path
     */
    public String getPath() {
        return path.replace("fakepath", "AccountActivitiesMonitor");
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the titleRow
     */
    public int getTitleRow() {
        return titleRow;
    }

    /**
     * @param titleRow the titleRow to set
     */
    public void setTitleRow(int titleRow) {
        this.titleRow = titleRow;
    }
    
    /**
     * @return the titleRow
     */
    public int getTitleRowIndex() {
        return titleRow-1;
    }

    /**
     * @return the numOfColumns
     */
    public int getNumOfColumns() {
        return numOfColumns;
    }

    /**
     * @param numOfColumns the numOfColumns to set
     */
    public void setNumOfColumns(int numOfColumns) {
        this.numOfColumns = numOfColumns;
    }

    /**
     * @return the titleMapping
     */
    public AccountActivityTitleMapping getTitleMapping() {
        return titleMapping;
    }

    /**
     * @param titleMapping the titleMapping to set
     */
    public void setTitleMapping(AccountActivityTitleMapping titleMapping) {
        this.titleMapping = titleMapping;
    }
    
}

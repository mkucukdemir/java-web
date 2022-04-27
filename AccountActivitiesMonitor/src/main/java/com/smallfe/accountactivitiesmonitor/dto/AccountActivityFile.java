/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.dto;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author mehmet.kucukdemir
 */
public class AccountActivityFile {
    
    private String path;
    private String fileName;
    private int titleRow;
    private int numOfColumns;
    private AccountActivityTitleMapping titleMapping;

    public AccountActivityFile(String path) {
        this.path = path;
        this.fileName = path.substring(path.lastIndexOf("\\")+1);
    }

    /**
     * @return the path
     */
    public String getPath() {
        return "import\\" + fileName;
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

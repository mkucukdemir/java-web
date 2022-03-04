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
public class AccountActivityTitleMapping {
    
    private String filePath;
    private Integer timestampIndex;
    private Integer infoIndex;
    private Integer paymentIndex;

    /**
     * @return the filePath
     */
    public String getFilePath() {
        return this.filePath.replace("fakepath", "AccountActivitiesMonitor");
    }

    /**
     * @param filePath the filePath to set
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * @return the timestampIndex
     */
    public Integer getTimestampIndex() {
        return timestampIndex;
    }

    /**
     * @param timestampIndex the timestampIndex to set
     */
    public void setTimestampIndex(Integer timestampIndex) {
        this.timestampIndex = timestampIndex;
    }

    /**
     * @return the infoIndex
     */
    public Integer getInfoIndex() {
        return infoIndex;
    }

    /**
     * @param infoIndex the infoIndex to set
     */
    public void setInfoIndex(Integer infoIndex) {
        this.infoIndex = infoIndex;
    }

    /**
     * @return the paymentIndex
     */
    public Integer getPaymentIndex() {
        return paymentIndex;
    }

    /**
     * @param paymentIndex the paymentIndex to set
     */
    public void setPaymentIndex(Integer paymentIndex) {
        this.paymentIndex = paymentIndex;
    }
    
}

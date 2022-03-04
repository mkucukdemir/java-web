/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.dto;

import java.util.Date;

/**
 *
 * @author mehmet.kucukdemir
 */
public class AccountActivity {
    
    private String id;
    private Date timestamp;
    private String info;
    private String payment;
    private String category;
    
    public AccountActivity(Date timestamp, String info, String payment,String category,int id) {
        this.id = String.valueOf(id);
        this.timestamp = timestamp;
        this.info = info;
        this.payment = payment;
        this.category = category;
    }

    public AccountActivity(String id, Date timestamp, String info, String payment, String category) {
        this.id = id;
        this.timestamp = timestamp;
        this.info = info;
        this.payment = payment;
        this.category = category;
    }
    
    /**
     * @return the timestamp
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * @return the payment
     */
    public String getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(String payment) {
        this.payment = payment;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    public String getPaymentForBigDecimal() {
        return this.payment
                .replace("-", "")
                .replace(".", "")
                .replace(",", "");
    }
    
}

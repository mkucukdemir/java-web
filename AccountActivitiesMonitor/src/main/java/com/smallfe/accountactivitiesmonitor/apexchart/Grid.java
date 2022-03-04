/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.apexchart;

/**
 *
 * @author mehmet.kucukdemir
 */
class Grid {
    
    private String borderColor;
    private Row row;

    /**
     * @return the borderColor
     */
    public String getBorderColor() {
        return borderColor;
    }

    /**
     * @param borderColor the borderColor to set
     */
    public void setBorderColor(String borderColor) {
        this.borderColor = borderColor;
    }

    /**
     * @return the row
     */
    public Row getRow() {
        return row;
    }

    /**
     * @param row the row to set
     */
    public void setRow(Row row) {
        this.row = row;
    }
    
}

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
class Legend {
    
    private String position;
    private String horizontalAlign;
    private boolean floating;
    private int offsetY;
    private int offsetX;

    /**
     * @return the position
     */
    public String getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(String position) {
        this.position = position;
    }

    /**
     * @return the horizontalAlign
     */
    public String getHorizontalAlign() {
        return horizontalAlign;
    }

    /**
     * @param horizontalAlign the horizontalAlign to set
     */
    public void setHorizontalAlign(String horizontalAlign) {
        this.horizontalAlign = horizontalAlign;
    }

    /**
     * @return the floating
     */
    public boolean isFloating() {
        return floating;
    }

    /**
     * @param floating the floating to set
     */
    public void setFloating(boolean floating) {
        this.floating = floating;
    }

    /**
     * @return the offsetY
     */
    public int getOffsetY() {
        return offsetY;
    }

    /**
     * @param offsetY the offsetY to set
     */
    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    /**
     * @return the offsetX
     */
    public int getOffsetX() {
        return offsetX;
    }

    /**
     * @param offsetX the offsetX to set
     */
    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }
    
}

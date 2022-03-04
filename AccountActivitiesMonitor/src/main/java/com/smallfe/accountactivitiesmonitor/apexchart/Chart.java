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
class Chart {
    
    private String type;
    private boolean stacked;
    private int height;
    private Zoom zoom;
    private Toolbar toolbar;

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
     * @return the stacked
     */
    public boolean isStacked() {
        return stacked;
    }

    /**
     * @param stacked the stacked to set
     */
    public void setStacked(boolean stacked) {
        this.stacked = stacked;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the zoom
     */
    public Zoom getZoom() {
        return zoom;
    }

    /**
     * @param zoom the zoom to set
     */
    public void setZoom(Zoom zoom) {
        this.zoom = zoom;
    }

    /**
     * @return the toolbar
     */
    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * @param toolbar the toolbar to set
     */
    public void setToolbar(Toolbar toolbar) {
        this.toolbar = toolbar;
    }
    
}

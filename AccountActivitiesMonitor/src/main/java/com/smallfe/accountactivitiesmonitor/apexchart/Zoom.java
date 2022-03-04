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
class Zoom {
    
    private String type;
    private boolean enabled;
    private boolean autoScaleYaxis;

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
     * @return the enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * @param enabled the enabled to set
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return the autoScaleYaxis
     */
    public boolean isAutoScaleYaxis() {
        return autoScaleYaxis;
    }

    /**
     * @param autoScaleYaxis the autoScaleYaxis to set
     */
    public void setAutoScaleYaxis(boolean autoScaleYaxis) {
        this.autoScaleYaxis = autoScaleYaxis;
    }
    
}

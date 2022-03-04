/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.apexchart;

import java.util.List;

/**
 *
 * @author mehmet.kucukdemir
 */
class Gradient {
    
    private int shadeIntensity;
    private boolean inverseColors;
    private float opacityFrom;
    private float opacityTo;
    private List<Integer> stops;

    /**
     * @return the shadeIntensity
     */
    public int getShadeIntensity() {
        return shadeIntensity;
    }

    /**
     * @param shadeIntensity the shadeIntensity to set
     */
    public void setShadeIntensity(int shadeIntensity) {
        this.shadeIntensity = shadeIntensity;
    }

    /**
     * @return the inverseColors
     */
    public boolean isInverseColors() {
        return inverseColors;
    }

    /**
     * @param inverseColors the inverseColors to set
     */
    public void setInverseColors(boolean inverseColors) {
        this.inverseColors = inverseColors;
    }

    /**
     * @return the opacityFrom
     */
    public float getOpacityFrom() {
        return opacityFrom;
    }

    /**
     * @param opacityFrom the opacityFrom to set
     */
    public void setOpacityFrom(float opacityFrom) {
        this.opacityFrom = opacityFrom;
    }

    /**
     * @return the opacityTo
     */
    public float getOpacityTo() {
        return opacityTo;
    }

    /**
     * @param opacityTo the opacityTo to set
     */
    public void setOpacityTo(float opacityTo) {
        this.opacityTo = opacityTo;
    }

    /**
     * @return the stops
     */
    public List<Integer> getStops() {
        return stops;
    }

    /**
     * @param stops the stops to set
     */
    public void setStops(List<Integer> stops) {
        this.stops = stops;
    }
    
}

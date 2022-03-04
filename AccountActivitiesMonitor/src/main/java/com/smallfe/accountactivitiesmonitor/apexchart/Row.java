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
class Row {
    
    private List<String> colors;
    private float opacity;

    /**
     * @return the colors
     */
    public List<String> getColors() {
        return colors;
    }

    /**
     * @param colors the colors to set
     */
    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    /**
     * @return the opacity
     */
    public float getOpacity() {
        return opacity;
    }

    /**
     * @param opacity the opacity to set
     */
    public void setOpacity(float opacity) {
        this.opacity = opacity;
    }
    
}

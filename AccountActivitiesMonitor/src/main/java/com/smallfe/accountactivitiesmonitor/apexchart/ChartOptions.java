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
public class ChartOptions {
    
    private List<Serie> series;
    private Chart chart;
    private List<String> colors;
    private DataLabels dataLabels;
    private Stroke stroke;
    private Markers markers;
    private Title title;
    private Grid grid;
    private Fill fill;
    private YAxis yaxis;
    private XAxis xaxis;
    private Tooltip tooltip;
    private Legend legend;

    /**
     * @return the series
     */
    public List<Serie> getSeries() {
        return series;
    }

    /**
     * @param series the series to set
     */
    public void setSeries(List<Serie> series) {
        this.series = series;
    }

    /**
     * @return the chart
     */
    public Chart getChart() {
        return chart;
    }

    /**
     * @param chart the chart to set
     */
    public void setChart(Chart chart) {
        this.chart = chart;
    }

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
     * @return the dataLabels
     */
    public DataLabels getDataLabels() {
        return dataLabels;
    }

    /**
     * @param dataLabels the dataLabels to set
     */
    public void setDataLabels(DataLabels dataLabels) {
        this.dataLabels = dataLabels;
    }

    /**
     * @return the stroke
     */
    public Stroke getStroke() {
        return stroke;
    }

    /**
     * @param stroke the stroke to set
     */
    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    /**
     * @return the markers
     */
    public Markers getMarkers() {
        return markers;
    }

    /**
     * @param markers the markers to set
     */
    public void setMarkers(Markers markers) {
        this.markers = markers;
    }

    /**
     * @return the title
     */
    public Title getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(Title title) {
        this.title = title;
    }

    /**
     * @return the grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * @param grid the grid to set
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * @return the fill
     */
    public Fill getFill() {
        return fill;
    }

    /**
     * @param fill the fill to set
     */
    public void setFill(Fill fill) {
        this.fill = fill;
    }

    /**
     * @return the yaxis
     */
    public YAxis getYaxis() {
        return yaxis;
    }

    /**
     * @param yaxis the yaxis to set
     */
    public void setYaxis(YAxis yaxis) {
        this.yaxis = yaxis;
    }

    /**
     * @return the xaxis
     */
    public XAxis getXaxis() {
        return xaxis;
    }

    /**
     * @param xaxis the xaxis to set
     */
    public void setXaxis(XAxis xaxis) {
        this.xaxis = xaxis;
    }

    /**
     * @return the tooltip
     */
    public Tooltip getTooltip() {
        return tooltip;
    }

    /**
     * @param tooltip the tooltip to set
     */
    public void setTooltip(Tooltip tooltip) {
        this.tooltip = tooltip;
    }

    /**
     * @return the legend
     */
    public Legend getLegend() {
        return legend;
    }

    /**
     * @param legend the legend to set
     */
    public void setLegend(Legend legend) {
        this.legend = legend;
    }
    
}

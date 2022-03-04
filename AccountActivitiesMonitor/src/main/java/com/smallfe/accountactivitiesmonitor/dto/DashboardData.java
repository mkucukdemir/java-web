/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.dto;

import com.smallfe.accountactivitiesmonitor.SessionData;
import com.smallfe.accountactivitiesmonitor.apexchart.Serie;
import com.smallfe.accountactivitiesmonitor.util.MiscUtil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author mehmet.kucukdemir
 */
public class DashboardData {
    
    private transient  final String startDate;
    private transient  final String endDate;
    
    private List<Serie> daily;
    private List<Serie> monthly;
    private List<Serie> yearly;
    
    private transient  final DateFormat dailyDateFormat;
    private transient  final DateFormat monthlyDateFormat;
    private transient  final DateFormat yearlyDateFormat;
    
    private transient  final Map<String,Serie> dailySeries;
    private transient  final Map<String,Serie> monthlySeries;
    private transient  final Map<String,Serie> yearlySeries;

    public DashboardData(String startDate, String endDate) {
        this.dailyDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        this.monthlyDateFormat = new SimpleDateFormat("MM/yyyy");
        this.yearlyDateFormat = new SimpleDateFormat("yyyy");
        
        this.dailySeries = new HashMap<>();
        this.monthlySeries = new HashMap<>();
        this.yearlySeries = new HashMap<>();
        
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * @return the daily
     */
    public List<Serie> getDaily() {
        return daily;
    }

    /**
     * @param daily the daily to set
     */
    public void setDaily(List<Serie> daily) {
        this.daily = daily;
    }

    /**
     * @return the monthly
     */
    public List<Serie> getMonthly() {
        return monthly;
    }

    /**
     * @param monthly the monthly to set
     */
    public void setMonthly(List<Serie> monthly) {
        this.monthly = monthly;
    }

    /**
     * @return the yearly
     */
    public List<Serie> getYearly() {
        return yearly;
    }

    /**
     * @param yearly the yearly to set
     */
    public void setYearly(List<Serie> yearly) {
        this.yearly = yearly;
    }

    public void prepareFor(SessionData sessionData) {
        List<Date> dailyDatesInPeriod;
        try {
            dailyDatesInPeriod = MiscUtil.getDatesBetween(dailyDateFormat.parse(startDate), dailyDateFormat.parse(endDate));
        } catch (ParseException ex) {
            Logger.getLogger(DashboardData.class.getName()).log(Level.SEVERE, null, ex);
            dailyDatesInPeriod = new ArrayList<>();
        }
        
        sessionData.getCategories().forEach((categoryName,category)->{
            dailySeries.put(categoryName, new Serie(categoryName));
            monthlySeries.put(categoryName, new Serie(categoryName));
            yearlySeries.put(categoryName, new Serie(categoryName));
        });
        
        // For each day
        dailyDatesInPeriod.forEach((date)->{
            
            // Store payments according to categories
            Map<String,Long> paymentsOnDateOfCategories = new HashMap<>();
            // Get activities in date
            // If there exists a payment on date
            if(sessionData.getAccountActivitiesByDate().containsKey(date)){
                // Calculate total payment for the category on date
                sessionData.getAccountActivitiesByDate().get(date).forEach((activity)->{
                    
                    if(paymentsOnDateOfCategories.containsKey(activity.getCategory())){
                        // Cumulate
                        Long oldValue = paymentsOnDateOfCategories.get(activity.getCategory());
                        paymentsOnDateOfCategories.replace(activity.getCategory(), oldValue+Long.valueOf(activity.getPaymentForBigDecimal()));
                    }else{
                        // Add new
                        paymentsOnDateOfCategories.put(activity.getCategory(), Long.valueOf(activity.getPaymentForBigDecimal()));
                    }
                    
                });
            }
            // paymentsOnDateOfCategories has a one day cumulative payment according to category names
            
            // According to their category, populate them into serie list
            // populate on a day
            dailySeries.forEach((categoryName,dailyData)->{
                
                List<Long> data = new ArrayList<>();
                
                data.add(date.getTime());
                data.add(paymentsOnDateOfCategories.containsKey(categoryName)?paymentsOnDateOfCategories.get(categoryName):0L);
                dailyData.push(data);
                
            });

            // populate on a month
            monthlySeries.forEach((categoryName,monthlyData)->{
                
                List<Long> data = new ArrayList<>();
                
                try {
                    data.add(monthlyDateFormat.parse(monthlyDateFormat.format(date)).getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(DashboardData.class.getName()).log(Level.SEVERE, null, ex);
                    data.add(0L); //Exception
                }
                data.add(paymentsOnDateOfCategories.containsKey(categoryName)?paymentsOnDateOfCategories.get(categoryName):0L);
                
                if(monthlyData.getData().size() > 0 && monthlyData.getTimeOfLastDataEntry().equals(data.get(0)))
                    monthlyData.accumulateOnLast(paymentsOnDateOfCategories.containsKey(categoryName)?paymentsOnDateOfCategories.get(categoryName):0L);
                else
                    monthlyData.push(data);
                
            });
            
            // populate on a year
            yearlySeries.forEach((categoryName,yearlyData)->{
                
                List<Long> data = new ArrayList<>();
                
                try {
                    data.add(yearlyDateFormat.parse(yearlyDateFormat.format(date)).getTime());
                } catch (ParseException ex) {
                    Logger.getLogger(DashboardData.class.getName()).log(Level.SEVERE, null, ex);
                    data.add(0L); //Exception
                }
                data.add(paymentsOnDateOfCategories.containsKey(categoryName)?paymentsOnDateOfCategories.get(categoryName):0L);
                if(yearlyData.getData().size() > 0 && yearlyData.getTimeOfLastDataEntry().equals(data.get(0)))
                    yearlyData.accumulateOnLast(paymentsOnDateOfCategories.containsKey(categoryName)?paymentsOnDateOfCategories.get(categoryName):0L);
                else
                    yearlyData.push(data);
                
            });
        });

        this.setDaily( new ArrayList<>(dailySeries.values()));
        this.setMonthly(new ArrayList<>(monthlySeries.values()));
        this.setYearly(new ArrayList<>(yearlySeries.values()));
    }
    
}

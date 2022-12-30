/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.controller;

import com.google.gson.Gson;
import com.smallfe.accountactivitiesmonitor.SessionDataContainer;
import com.smallfe.accountactivitiesmonitor.dto.AccountActivityTitleMapping;
import com.smallfe.accountactivitiesmonitor.dto.AccountActivityFile;
import com.smallfe.accountactivitiesmonitor.dto.Category;
import com.smallfe.accountactivitiesmonitor.dto.CategoryFile;
import com.smallfe.accountactivitiesmonitor.dto.DashboardData;
import com.smallfe.accountactivitiesmonitor.parser.ExcelParser;
import com.smallfe.accountactivitiesmonitor.parser.ExcelParserFactory;
import com.smallfe.accountactivitiesmonitor.parser.ExcelParserType;
import com.smallfe.accountactivitiesmonitor.parser.SearchBuilderOptionsParser;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author mehmet.kucukdemir
 */
@Controller
@RequestMapping ("/ajax/")
public class AjaxController {
    
    @PostMapping(path="/listCategoriesAndActivities",produces = "text/plain;charset=UTF-8")
    public @ResponseBody ResponseEntity<byte[]> handleListCategoriesAndActivities(HttpSession session) throws IOException {
        
        Gson gson = new Gson();
        Map<String,Object> data = new HashMap<>();
        
        try {
            SearchBuilderOptionsParser optionsParser = new SearchBuilderOptionsParser();
            List<Category> categoriesInClassPathResource = optionsParser.parseFromClassPathResource(SessionDataContainer.getInstance().getSessionData().get(session.getId()).getConfiguration().getImportDirPath() + "\\criterias.json");
            for (Category category : categoriesInClassPathResource) {
                SessionDataContainer.getInstance().getSessionData().get(session.getId()).getCategories().put(category.getName(), category);
            }
            data.put("categories",categoriesInClassPathResource);
        } catch (Exception e) {
            Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        
        data.put("activities", SessionDataContainer.getInstance().getSessionData().get(session.getId()).getAccountActivities());
        return new ResponseEntity<>(gson.toJson(data).getBytes("UTF-8"), HttpStatus.OK);
    }
    
    @PostMapping(path="/getDetailsOfCategory",consumes = "application/json;charset=UTF-8",produces = "text/plain;charset=UTF-8")
    public @ResponseBody ResponseEntity<byte[]> handleGetDetailsOfCategory(HttpSession session,@RequestBody String categoryname) throws IOException {
        
        Gson gson = new Gson();
        Map<String,Object> data = new HashMap<>();
        
        data.put("details", SessionDataContainer.getInstance().getSessionData().get(session.getId()).getCategories().get(categoryname).getDetails());
        
        return new ResponseEntity<>(gson.toJson(data).getBytes("UTF-8"), HttpStatus.OK);
    }
    
    @PostMapping(path="/addCategory",consumes = "application/json;charset=UTF-8",produces = "text/plain;charset=UTF-8")
    public @ResponseBody ResponseEntity<byte[]> handleAddCategory(HttpSession session,@RequestBody String categoryAsString) throws IOException {
        
        Gson gson = new Gson();
        Map<String,Object> data = new HashMap<>();
        
        SearchBuilderOptionsParser optionsParser = new SearchBuilderOptionsParser();
        Category category = optionsParser.parseFromJSON(categoryAsString);
        
        SessionDataContainer.getInstance().putIfNotExists(session.getId(),category);
        
        for (int accountActivityIndex : category.getAccountActivityIndexes()) 
            SessionDataContainer.getInstance().getSessionData().get(session.getId()).getAccountActivities().get(accountActivityIndex).setCategory(category.getName()); 
        
        data.put("activities", SessionDataContainer.getInstance().getSessionData().get(session.getId()).getAccountActivities());
        return new ResponseEntity<>(gson.toJson(data).getBytes("UTF-8"), HttpStatus.OK);
        
    }
    
    @PostMapping(path="/autocompleteCategoryNames",produces = "text/plain;charset=UTF-8")
    public @ResponseBody ResponseEntity<byte[]> handleFilternames(HttpSession session,@RequestParam("term") String categoryName) throws IOException {
        
        Gson gson = new Gson();
        Map<String,Category> categories = SessionDataContainer.getInstance().getSessionData().get(session.getId()).getCategories();
//        categories = new HashMap<>(SessionDataContainer.getInstance().getSessionData().get(session.getId()).getCategories());
        categories.put(categoryName,new Category(categoryName));
        
        return new ResponseEntity<>(gson.toJson(categories).getBytes("UTF-8"), HttpStatus.OK);
        
    }
    
    @GetMapping(path="/exportCategories",produces = "text/plain;charset=UTF-8")
    public @ResponseBody ResponseEntity<byte[]> handleExportCategories(HttpSession session) throws UnsupportedEncodingException {
        
        Gson gson = new Gson();
        Map<String,Object> data = new HashMap<>();
        
        CategoryFile file = new CategoryFile("criterias.json");
        
        try {
            file.writeCategories(new ArrayList<>(SessionDataContainer.getInstance().getSessionData().get(session.getId()).getCategories().values()));
            data.put("status", "Success");
        } catch (IOException ex) {
            Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, ex);
            data.put("status", "Error");
        }
        
        return new ResponseEntity<>(gson.toJson(data).getBytes("UTF-8"), HttpStatus.OK);
        
    }
    
    @GetMapping(path="/viewdashboard",produces = "text/plain;charset=UTF-8")
    public @ResponseBody ResponseEntity<byte[]> handleViewDashboard(HttpSession session) throws UnsupportedEncodingException, ParseException {
        Gson gson = new Gson();
        String startDate = "01/01/2019";
        Date today = new Date();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyy");
        DashboardData dashboardData = new DashboardData(startDate,format.format(today));
        
        dashboardData.prepareFor(SessionDataContainer.getInstance().getSessionData().get(session.getId()));
        
        // daily : [...name-data for all class...], monthly : [...name-data for all class...], yearly : [...name-data for all class...]
        return new ResponseEntity<>(gson.toJson(dashboardData).getBytes("UTF-8"), HttpStatus.OK);
        
    }
    
    @GetMapping(path = "/viewdashboard/{key}",produces = "text/plain;charset=UTF-8")
    public @ResponseBody ResponseEntity<byte[]> handleGraphByKey(HttpSession session,@PathVariable String key) {
        // Get data from session
        // Prepare JSON response for the graph
        return null;
    }
}

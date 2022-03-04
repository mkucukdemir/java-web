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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @PostMapping(path="/uploadAccountActivityFile",produces = "text/plain;charset=UTF-8")
    public @ResponseBody ResponseEntity<byte[]> handleUploadAccountActivity(HttpSession session,@RequestBody AccountActivityFile file) throws IOException {
        SessionDataContainer.getInstance().putIfNotExists(session.getId(),file);
        
        ExcelParser excelParser = ExcelParserFactory.createExcelParser(file.getTitleRowIndex(),file.getNumOfColumns(),ExcelParserType.POI_EXCEL_PARSER);
        Map<Integer,String> titles = excelParser.parseTitle(file.getPath());
        
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(titles).getBytes("UTF-8"), HttpStatus.OK);
    }
    
    @PostMapping(path="/setAccountActivityTitleMapping",produces = "text/plain;charset=UTF-8")
    public @ResponseBody ResponseEntity<byte[]> handleMatchTitles(HttpSession session,@RequestBody AccountActivityTitleMapping mapping) throws IOException {
        
        Gson gson = new Gson();
        Map<String,Object> data = new HashMap<>();
        
        if(SessionDataContainer.getInstance().isAccountActivityFileExists(session.getId(),mapping.getFilePath())){
            SessionDataContainer.getInstance().getSessionData().get(session.getId()).getAccountActivityFiles().get(mapping.getFilePath()).setTitleMapping(mapping);
        
            ExcelParser excelParser = ExcelParserFactory.createExcelParser(
                    SessionDataContainer.getInstance().getTitleRow(session.getId(),mapping.getFilePath()),
                    SessionDataContainer.getInstance().getNumOfColumns(session.getId(),mapping.getFilePath()),
                    ExcelParserType.POI_EXCEL_PARSER);

            excelParser.collectActivities(session.getId(),mapping.getFilePath());
            data.put("status", "Success");
        }
        else{
            data.put("status", "Error");
        }
        
        return new ResponseEntity<>(gson.toJson(data).getBytes("UTF-8"), HttpStatus.OK);
        
    }
    
    @PostMapping(path="/listCategoriesAndActivities",produces = "text/plain;charset=UTF-8")
    public @ResponseBody ResponseEntity<byte[]> handleListCategoriesAndActivities(HttpSession session) throws IOException {
        
        Gson gson = new Gson();
        Map<String,Object> data = new HashMap<>();
        
        SearchBuilderOptionsParser optionsParser = new SearchBuilderOptionsParser();
        List<Category> categoriesInClassPathResource = optionsParser.parseFromClassPathResource("criterias.json");
        for (Category category : categoriesInClassPathResource) {
            SessionDataContainer.getInstance().getSessionData().get(session.getId()).getCategories().put(category.getName(), category);
        }
        data.put("categories",categoriesInClassPathResource);
        
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
        DashboardData dashboardData = new DashboardData("01/01/2019","22/02/2022");
        
        dashboardData.prepareFor(SessionDataContainer.getInstance().getSessionData().get(session.getId()));
        
        return new ResponseEntity<>(gson.toJson(dashboardData).getBytes("UTF-8"), HttpStatus.OK);
        
    }
    
}

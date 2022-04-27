/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.controller;

import com.google.gson.Gson;
import com.smallfe.accountactivitiesmonitor.SessionDataContainer;
import com.smallfe.accountactivitiesmonitor.dto.AccountActivityFile;
import com.smallfe.accountactivitiesmonitor.dto.Category;
import com.smallfe.accountactivitiesmonitor.parser.ExcelParser;
import com.smallfe.accountactivitiesmonitor.parser.ExcelParserFactory;
import com.smallfe.accountactivitiesmonitor.parser.ExcelParserType;
import com.smallfe.accountactivitiesmonitor.parser.SearchBuilderOptionsParser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author mehmet.kucukdemir
 */
@Controller
@RequestMapping ("/")
public class FormController {
    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView handleHomePageRequest(HttpServletRequest request) throws Exception {
        SessionDataContainer.getInstance().initIfNotExists(request.getSession().getId());
        Gson gson = new Gson();
        Map<String,Object> data = new HashMap<>();
        ModelAndView model = new ModelAndView("mainPage");
        
        List<AccountActivityFile> accountActivityFiles = SessionDataContainer.getInstance().getSessionData().get(request.getSession().getId()).getConfiguration().getImportAccountActivitiesFileProperties();
        for (AccountActivityFile accountActivityFile : accountActivityFiles) {
            
            ExcelParser excelParser = ExcelParserFactory.createExcelParser(
                    accountActivityFile.getTitleRowIndex(),
                    accountActivityFile.getNumOfColumns(),
                    ExcelParserType.POI_EXCEL_PARSER);

            excelParser.collectActivities(request.getSession().getId(),accountActivityFile);
        }
        
        // Categories for searchbuilder
        try {
            List<Category> categoriesInClassPathResource = SessionDataContainer.getInstance().getSessionData().get(request.getSession().getId()).getConfiguration().getCategoriesInClassPathResource();
            for (Category category : categoriesInClassPathResource) {
                SessionDataContainer.getInstance().putIfNotExists(request.getSession().getId(),category);
            }
            data.put("categories",categoriesInClassPathResource);
        } catch (Exception e) {
            Logger.getLogger(AjaxController.class.getName()).log(Level.SEVERE, null, e);
        }
        
        data.put("activities", SessionDataContainer.getInstance().getSessionData().get(request.getSession().getId()).getAccountActivities());
        model.addObject("data", gson.toJson(data));
        return model;
    }
    
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView handleDashboardPageRequest(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("dashboard");
        return model;
    }
    
}

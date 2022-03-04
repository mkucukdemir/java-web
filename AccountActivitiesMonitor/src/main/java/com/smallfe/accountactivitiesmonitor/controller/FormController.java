/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.controller;

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
    public ModelAndView handleHomePageRequest() throws Exception {
        ModelAndView model = new ModelAndView("mainPage");
        return model;
    }
    
    @RequestMapping(value = {"/dashboard"}, method = RequestMethod.GET)
    public ModelAndView handleDashboardPageRequest(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("dashboard");
        return model;
    }
    
}

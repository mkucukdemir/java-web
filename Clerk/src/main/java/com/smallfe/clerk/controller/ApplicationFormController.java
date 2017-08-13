/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smallfe.clerk.controller;

import com.google.gson.Gson;
import java.io.OutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Write short comment related with created class
 * @author mkucukdemir
 */
@Controller
@RequestMapping ("/")
public class ApplicationFormController {
    
    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView handleQueryForm(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("mainPage");
        return model;
    }
    
    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public ModelAndView handleAboutForm(HttpServletRequest request) throws Exception {
        ModelAndView model = new ModelAndView("about");
        return model;
    }
    
    @RequestMapping(value = {"/ajax/query"}, method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void handleQuery(HttpServletRequest request, HttpServletResponse response, BindingResult errors) throws Exception{
        String json;
        Gson gson = new Gson();
        json = gson.toJson(null);
        response.setContentType("application/json; charset=ISO-8859-9");
        OutputStream os = response.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();
    }
 
    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
       if (auth != null){    
          new SecurityContextLogoutHandler().logout(request, response, auth);
       }
       return "mainPage";
    }
 
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("user", getPrincipal());
        return "unAuthorizedUserPage";
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            userName = ((UserDetails)principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}

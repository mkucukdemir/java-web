/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.smallfe.webapplicationtemplate.controller;

import com.smallfe.webapplicationtemplate.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Write short comment related with created class
 * @author mkucukdemir
 */
@Controller
@RequestMapping ("/ajax/")
public class ApplicationAjaxController {
    
    @Autowired
    DataService dataService;

}

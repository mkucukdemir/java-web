/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.parser;

import com.smallfe.accountactivitiesmonitor.dto.AccountActivityFile;
import java.io.File;
import java.util.Map;

/**
 *
 * @author mehmet.kucukdemir
 */
public interface ExcelParser {
    
    public Map<Integer,String> parseTitle(String filePath);
    public void collectActivities(String sessionId,AccountActivityFile accountActivityFile);
    
}

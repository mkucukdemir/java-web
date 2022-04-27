/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.parser;

import com.smallfe.accountactivitiesmonitor.SessionDataContainer;
import com.smallfe.accountactivitiesmonitor.dto.AccountActivity;
import com.smallfe.accountactivitiesmonitor.dto.AccountActivityFile;
import com.smallfe.accountactivitiesmonitor.dto.AccountActivityTitleMapping;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;


/**
 *
 * @author mehmet.kucukdemir
 */
public class PoiExcelParser implements ExcelParser {
    
    private final int titleRow;
    private final int numberOfColumns;
    private DataFormatter formatter;

    public PoiExcelParser(int titleRow, int numberOfColumns) {
        this.titleRow = titleRow;
        this.numberOfColumns = numberOfColumns;
        this.formatter = new DataFormatter();
    }
    
    private boolean isValid(Row row) {
        return row.getRowNum()>titleRow;
    }

    private boolean isEmpty(Row row,AccountActivityTitleMapping mapping) {
        
        try {
            return row.getCell(mapping.getTimestampIndex()).getRichStringCellValue().getString().isEmpty() && 
                row.getCell(mapping.getInfoIndex()).getRichStringCellValue().getString().isEmpty() &&
                formatter.formatCellValue(row.getCell(mapping.getPaymentIndex())).isEmpty();
        } catch (Exception e) {
            return true;
        }
        
    }

    @Override
    public Map<Integer, String> parseTitle(String resourceFilePath) {
        
        Map<Integer,String> titles = new HashMap<>();
        try {
            Resource resource = new ClassPathResource(resourceFilePath);
            Workbook workbook = WorkbookFactory.create(resource.getFile());
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(titleRow);
            for(int j = 0;j<numberOfColumns;j++){
                Cell cell = row.getCell(j);
                titles.put(j, cell.getRichStringCellValue().getString());
            }
        } catch (IOException ex) {
            Logger.getLogger(PoiExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(PoiExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return titles;
        
    }

    @Override
    public void collectActivities(String sessionId,AccountActivityFile accountActivityFile) {
        
        List<AccountActivity> activities = parseFile(sessionId,accountActivityFile);
        
        SessionDataContainer.getInstance().getSessionData().get(sessionId).getAccountActivities().addAll(activities);

        
        for (AccountActivity accountActivity : activities) {
            if(SessionDataContainer.getInstance().getSessionData().get(sessionId).getAccountActivitiesByDate().containsKey(accountActivity.getTimestamp())) {
                if(!SessionDataContainer.getInstance().getSessionData().get(sessionId).getAccountActivitiesByDate().get(accountActivity.getTimestamp()).contains(accountActivity))
                    SessionDataContainer.getInstance().getSessionData().get(sessionId).getAccountActivitiesByDate().get(accountActivity.getTimestamp()).add(accountActivity);
            }
            else {
                SessionDataContainer.getInstance().getSessionData().get(sessionId).getAccountActivitiesByDate().put(accountActivity.getTimestamp(),new ArrayList<>());
                SessionDataContainer.getInstance().getSessionData().get(sessionId).getAccountActivitiesByDate().get(accountActivity.getTimestamp()).add(accountActivity);
            }
        }
        
    }

    private List<AccountActivity> parseFile(String sessionId, AccountActivityFile accountActivityFile) {
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        int sizeOfAccountActivities = SessionDataContainer.getInstance().getSessionData().get(sessionId).getAccountActivities().size();
        AccountActivityTitleMapping titleMapping = accountActivityFile.getTitleMapping();

        List<AccountActivity> activities = new ArrayList();
        try {
            // import // filename
            Resource resource = new ClassPathResource(accountActivityFile.getPath());
            Workbook workbook = WorkbookFactory.create(resource.getFile());
            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if(!isValid(row))
                    continue;
                if(isEmpty(row,titleMapping))
                    break;
                Date timestamp = dateFormat.parse(row.getCell(titleMapping.getTimestampIndex()).getRichStringCellValue().getString());
                String info = row.getCell(titleMapping.getInfoIndex()).getRichStringCellValue().getString();
                String payment = formatter.formatCellValue(row.getCell(titleMapping.getPaymentIndex()));
                String category = "Unclassified";
                activities.add(new AccountActivity(timestamp,info,payment,category,sizeOfAccountActivities++));
            }
            workbook.close();
        } catch (IOException ex) {
            Logger.getLogger(PoiExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncryptedDocumentException ex) {
            Logger.getLogger(PoiExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(PoiExcelParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return activities;
        
    }
    
}

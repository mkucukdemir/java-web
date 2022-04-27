/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.configuration;

import com.smallfe.accountactivitiesmonitor.dto.AccountActivityFile;
import com.smallfe.accountactivitiesmonitor.dto.Category;
import java.util.List;

/**
 *
 * @author mehmet.kucukdemir
 */
public interface Configuration {
    public void init();
    public String getImportDirPath();
    public String getExportDirPath();
    public List<AccountActivityFile> getImportAccountActivitiesFileProperties();
    public List<Category> getCategoriesInClassPathResource();
}

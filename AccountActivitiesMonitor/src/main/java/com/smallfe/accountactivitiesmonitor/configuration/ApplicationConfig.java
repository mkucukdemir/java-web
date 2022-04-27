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
public class ApplicationConfig {
    
    private String importDirRelativePath;
    private String exportDirRelativePath;
    private List<AccountActivityFile> importAccountActivitiesFileProperties;
    private List<Category> categoriesInClassPathResource;

    /**
     * @return the importDirRelativePath
     */
    public String getImportDirRelativePath() {
        return importDirRelativePath;
    }

    /**
     * @param importDirRelativePath the importDirRelativePath to set
     */
    public void setImportDirRelativePath(String importDirRelativePath) {
        this.importDirRelativePath = importDirRelativePath;
    }

    /**
     * @return the exportDirRelativePath
     */
    public String getExportDirRelativePath() {
        return exportDirRelativePath;
    }

    /**
     * @param exportDirRelativePath the exportDirRelativePath to set
     */
    public void setExportDirRelativePath(String exportDirRelativePath) {
        this.exportDirRelativePath = exportDirRelativePath;
    }

    /**
     * @return the importAccountActivitiesFileProperties
     */
    public List<AccountActivityFile> getImportAccountActivitiesFileProperties() {
        return importAccountActivitiesFileProperties;
    }

    /**
     * @param importAccountActivitiesFileProperties the importAccountActivitiesFileProperties to set
     */
    public void setImportAccountActivitiesFileProperties(List<AccountActivityFile> importAccountActivitiesFileProperties) {
        this.importAccountActivitiesFileProperties = importAccountActivitiesFileProperties;
    }

    /**
     * @return the categoriesInClassPathResource
     */
    public List<Category> getCategoriesInClassPathResource() {
        return categoriesInClassPathResource;
    }

    /**
     * @param categoriesInClassPathResource the categoriesInClassPathResource to set
     */
    public void setCategoriesInClassPathResource(List<Category> categoriesInClassPathResource) {
        this.categoriesInClassPathResource = categoriesInClassPathResource;
    }
    
}

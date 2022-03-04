/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.configuration;

/**
 *
 * @author mehmet.kucukdemir
 */
public class JSONConfiguration implements Configuration {
    
    private String projectDirFullPath;
    private String importDirRelativePath;
    private String exportDirRelativePath;

    @Override
    public void init() {
        
    }

    @Override
    public String getProjectDirFullPath() {
        return projectDirFullPath;
    }

    @Override
    public String getImportDirFullPath() {
        return projectDirFullPath + importDirRelativePath;
    }

    @Override
    public String getExportDirFullPath() {
        return projectDirFullPath + exportDirRelativePath;
    }
    
}

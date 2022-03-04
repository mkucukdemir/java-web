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
public interface Configuration {
    public void init();
    public String getProjectDirFullPath();
    public String getImportDirFullPath();
    public String getExportDirFullPath();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.configuration;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.smallfe.accountactivitiesmonitor.dto.AccountActivityFile;
import com.smallfe.accountactivitiesmonitor.dto.Category;
import com.smallfe.accountactivitiesmonitor.parser.SearchBuilderOptionsParser;
import java.io.FileReader;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 *
 * @author mehmet.kucukdemir
 */
public class JSONConfiguration implements Configuration {

    private ApplicationConfig app_config;

    public JSONConfiguration() {
        init();
    }

    @Override
    public void init() {
        try {
            Gson gson = new Gson();
            Resource resource = new ClassPathResource("config\\application_config.json");
            JsonReader reader = new JsonReader(new FileReader(resource.getFile()));

            app_config = gson.fromJson(reader, ApplicationConfig.class);

            reader.close();
            
            // Criterias
            SearchBuilderOptionsParser optionsParser = new SearchBuilderOptionsParser();
            app_config.setCategoriesInClassPathResource(optionsParser.parseFromClassPathResource(app_config.getImportDirRelativePath() + "\\criterias.json"));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public String getImportDirPath() {
        return app_config.getImportDirRelativePath();
    }

    @Override
    public String getExportDirPath() {
        return app_config.getExportDirRelativePath();
    }

    @Override
    public List<AccountActivityFile> getImportAccountActivitiesFileProperties() {
        return app_config.getImportAccountActivitiesFileProperties();
    }

    @Override
    public List<Category> getCategoriesInClassPathResource() {
        return app_config.getCategoriesInClassPathResource();
    }

}

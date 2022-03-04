/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.parser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smallfe.accountactivitiesmonitor.dto.Category;
import com.smallfe.accountactivitiesmonitor.util.CategoryDeserializer;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mehmet.kucukdemir
 */
public class SearchBuilderOptionsParser {

    public List<Category> parseFromClassPathResource(String classPathResource) {
        
        try {
            
            Resource resource = new ClassPathResource("criterias.json");
            InputStream resourceInputStream = resource.getInputStream();
            Reader reader = new InputStreamReader(resourceInputStream, Charset.forName("UTF-8"));

            Gson gsonBuilder = new GsonBuilder()
                    .registerTypeAdapter(Category.class, new CategoryDeserializer())
                    .create();
            Type listType = new TypeToken<ArrayList<Category>>(){}.getType();
            List<Category> categoriesInClassPathResource = gsonBuilder.fromJson(reader, listType);
            
            return categoriesInClassPathResource;

        } catch (Exception ex) {
            
            Logger.getLogger(SearchBuilderOptionsParser.class.getName()).log(Level.SEVERE, null, ex);
            return null;
            
        }
        
    }

    public Category parseFromJSON(String categoryAsString) {
        
        Gson gsonBuilder = new GsonBuilder()
                .registerTypeAdapter(Category.class, new CategoryDeserializer())
                .create();
        return gsonBuilder.fromJson(categoryAsString, Category.class);
        
    }


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.dto;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
/**
 *
 * @author mehmet.kucukdemir
 */
public class CategoryFile {
    
    private final String filename;
    private static final String DIR="C:\\AccountActivitiesMonitor\\";

    public CategoryFile(String filename) {
        this.filename = filename;
    }
    
    public String getPath(){
        return DIR + filename;
    }

    public void writeCategories(List<Category> categories) throws IOException {
        // create a writer
        Writer writer = new OutputStreamWriter(
                        new FileOutputStream(getPath()),"UTF-8");

        // convert map to JSON File
        Gson gson = new GsonBuilder().create();
        gson.toJson(categories, writer);

        // close the writer
        writer.close();
    }
    
}

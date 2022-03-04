/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.smallfe.accountactivitiesmonitor.dto.Category;
import com.smallfe.accountactivitiesmonitor.dto.Criteria;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author mehmet.kucukdemir
 */
public class CategoryDeserializer implements JsonDeserializer<Category>{

    @Override
    public Category deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonObject jsonObject = je.getAsJsonObject();
        String name = jsonObject.get("name").getAsString();
        List<Integer> activities = new ArrayList<>();
        for (JsonElement accountActivityIndex : jsonObject.get("accountActivityIndexes").getAsJsonArray()) {
            activities.add(accountActivityIndex.getAsInt());
        }
        
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Criteria.class, new CriteriaDeserializer())
                .create();
        Criteria details = gson.fromJson(jsonObject.get("details").toString(), Criteria.class);
        
        return new Category(name, details, activities);
    }
    
}

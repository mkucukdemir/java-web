/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.smallfe.accountactivitiesmonitor.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.smallfe.accountactivitiesmonitor.dto.Criteria;
import com.smallfe.accountactivitiesmonitor.dto.SearchBuilderCriteria;
import com.smallfe.accountactivitiesmonitor.dto.SearchBuilderDetails;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author mehmet.kucukdemir
 */
public class CriteriaDeserializer implements JsonDeserializer<Criteria>{

    @Override
    public Criteria deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        JsonObject jsonObject = je.getAsJsonObject();
        if(jsonObject.has("criteria")){
            SearchBuilderDetails searchBuilderDetails = new SearchBuilderDetails();
            searchBuilderDetails.setLogic(jsonObject.get("logic").getAsString());
            List<Criteria> criteriasList = new ArrayList<>();
            JsonArray criterias = jsonObject.get("criteria").getAsJsonArray();
            for (JsonElement criteria : criterias) {
                criteriasList.add(deserialize(criteria, type, jdc));
            }
            searchBuilderDetails.setCriteria(criteriasList);
            return searchBuilderDetails;
        }
        else {
            List<String> value = new ArrayList<>();
            for (JsonElement _value : jsonObject.get("value").getAsJsonArray()) {
                value.add(_value.getAsString());
            }
            return new SearchBuilderCriteria(
                jsonObject.get("condition").getAsString(),
                jsonObject.get("data").getAsString(),
                jsonObject.get("origData").getAsString(),
                jsonObject.get("type").getAsString(),
                value);
        }

    }
}

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
public class ConfigurationFactory {
    
    public static Configuration createConfiguration(ConfigurationType type){
        switch(type){
            case JSON_FILE:
                return new JSONConfiguration();
            default:
                return null;
        }
    }
    
}

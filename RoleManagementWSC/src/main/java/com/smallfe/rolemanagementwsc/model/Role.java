package com.smallfe.rolemanagementwsc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Write short comment on created class
 * Created on 03.Nis.2016
 * @author mkucukdemir
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Role {
    
    private Integer id;
    private String name;
    private String info;

    public Role() {
    }

    public Role(Integer id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }
    
}

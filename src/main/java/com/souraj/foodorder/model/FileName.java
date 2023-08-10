/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ksouraj
 */
@Entity
@Table(name = "file_name")

public class FileName extends AbstractEntity {

    private String name;
    private Long fsize;
    private String location;
    private String allowedType;

    public FileName() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getFsize() {
        return fsize;
    }

    public void setFsize(Long fsize) {
        this.fsize = fsize;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAllowedType() {
        return allowedType;
    }

    public void setAllowedType(String allowedType) {
        this.allowedType = allowedType;
    }

   

    @Override
    public String getTableName() {
        return "file_name";
    }

}

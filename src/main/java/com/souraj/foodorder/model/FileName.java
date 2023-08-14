/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import jakarta.validation.constraints.NotEmpty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ksouraj
 */
@Entity
@Table(name = "file_name")

public class FileName extends AbstractEntity {

    @Column(name = "name")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Column(name = "file_size")
    private Integer fsize;
    @Column(name = "file_location")
    private String location;
    @Column(name = "allowed_File_Type")
    private String allowedType;

    public FileName() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFsize() {
        return fsize;
    }

    public void setFsize(Integer fsize) {
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

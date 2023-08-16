/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import jakarta.validation.constraints.NotEmpty;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ksouraj
 */
@Entity
@Table(name = "configuration")
public class Configuration extends AbstractEntity {

    @Column(name = "file_name")
    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @Column(name = "file_size")
    private int fsize;
    @Column(name = "file_location")
    private String location;
    @Column(name = "allowed_File_Type")
    private List<String> allowedType;

    public Configuration() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFsize() {
        return fsize;
    }

    public void setFsize(int fsize) {
        this.fsize = fsize;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<String> getAllowedType() {
        return allowedType;
    }

    public void setAllowedType(List<String> allowedType) {
        this.allowedType = allowedType;
    }



    @Override
    public String getTableName() {
        return "configuration";
    }

}

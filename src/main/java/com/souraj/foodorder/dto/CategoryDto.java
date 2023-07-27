/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.dto;

import javax.servlet.http.Part;

/**
 *
 * @author ksouraj
 */
public class CategoryDto {
    
    
    private String name;
    private Part image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Part getImage() {
        return image;
    }

    public void setImage(Part image) {
        this.image = image;
    }
    
    
    
    
    
}

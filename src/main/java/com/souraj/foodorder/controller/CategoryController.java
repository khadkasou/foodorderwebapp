/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.restClient.CategoryRestClient;
import java.io.IOException;

import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

@Named(value = "categoryController")
@ViewScoped
public class CategoryController implements Serializable {

    private Category category;
    private List<Category> categoryList;

    @Inject
    private CategoryRestClient categoryRestClient;

    public CategoryController() {
      
    }

    
    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoryList() {
        loadData();
        return this.categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public void beforeCreate() {
        this.category = new Category();
    }

    
    public void beforeUpdate(Long id) {
       this.category= this.categoryRestClient.getCategoryById(id);
    }

    public void addCategory() {
        categoryRestClient.createCategory(category);
        loadData();
    }

    public void update() throws IOException {
        categoryRestClient.updateCategory(this.category.getId(),this.category);
        
    }

    public void deleteById(Long id) {
        categoryRestClient.deleteCategoryById(id);
        
       
    }
 public void loadData() {
        this.categoryList = new ArrayList<>();
        this.categoryList = categoryRestClient.getAllCategories();

    }
    

    
   

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;
import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.repository.CategoryRepo;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;


/**
 *
 * @author ksouraj
 */
@Named(value = "categoryController")
@ViewScoped
public class CategoryController implements Serializable {

    private Category category;
    private List<Category> categoryList;
    
    private UploadedFile imageFile;

    @Inject
    private CategoryRepo categoryRepo;

    public UploadedFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(UploadedFile imageFile) {
        this.imageFile = imageFile;
    }

    
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @PostConstruct
    public void init() {
        this.category = new Category();
        loadData();
    }

    public void beforeCreate() {
        this.category = new Category();
    }

    public void beforeUpdate(Category ctg) {
        this.category = categoryRepo.findById(ctg.getId());
    }

    public void addCategory() {
        
        if (imageFile != null) {
            try {
                InputStream inputStream = imageFile.getInputstream();
                String imageFileName = "category_Id" + category.getId() + "_" + imageFile.getFileName();
                Files.copy(inputStream, Paths.get("home/ksouraj/uploaded", imageFileName));
                category.setImagePath("home/ksouraj/uploaded/" + imageFileName);
            } catch (IOException e) {
            }
        }

        categoryRepo.save(category);
        loadData();
    }

    public void update() {
        categoryRepo.update(this.category);
        loadData();
    }

    public void deleteById(Long id) {
        categoryRepo.delete(id);
        loadData();
    }

    public void loadData() {
        this.categoryList = new ArrayList<>();
        this.categoryList = categoryRepo.findAll();
    }
    
    
   
    
   
}

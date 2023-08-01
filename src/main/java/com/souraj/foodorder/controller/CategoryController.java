package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.model.File;
import com.souraj.foodorder.repository.CategoryRepo;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

@Named(value = "categoryController")
@ViewScoped
public class CategoryController implements Serializable {

    private Category category;
    private List<Category> categoryList;
    private UploadedFile uploadedFile; 

    @Inject
    private FileUploadController fileUploadController;

    @Inject
    private CategoryRepo categoryRepo;

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

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
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

    public void handleFileUpload(FileUploadEvent event) {
        uploadedFile = event.getFile(); 
    }

    public void addCategory() {
        if (uploadedFile != null) {
            File file = new File();
            file.setFileName(uploadedFile.getFileName());
            file.setFileSize((int) uploadedFile.getSize());
            String savedFilePath = fileUploadController.saveFile(uploadedFile);

            
            category.setFilePath(savedFilePath);
        }

        categoryRepo.save(category);
        category = new Category();
        uploadedFile = null; 
    }

    public void update() {
        if (uploadedFile != null) {
            File file = new File();
            file.setFileName(uploadedFile.getFileName());
            file.setFileSize((int) uploadedFile.getSize());
            String savedFilePath = fileUploadController.saveFile(uploadedFile);
            category.setFilePath(savedFilePath);
        }

        categoryRepo.update(category);
        category = new Category();
        uploadedFile = null; 
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

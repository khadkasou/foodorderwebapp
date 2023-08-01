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

@Named(value = "categoryController")
@ViewScoped
public class CategoryController implements Serializable {

    private Category category;
    private List<Category> categoryList;

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
        fileUploadController.handleFileUpload(event);
    }

    public void addCategory() {
        if (!fileUploadController.getUploadedFiles().isEmpty()) {
            File savedFile = fileUploadController.getUploadedFiles().get(0);
            category.setFilePath(savedFile.getFilePath() + "/" + savedFile.getFileName());
        }

        categoryRepo.save(category);
        category = new Category();
    }

    public void update() {
        if (!fileUploadController.getUploadedFiles().isEmpty()) {
            File savedFile = fileUploadController.getUploadedFiles().get(0);
            category.setFilePath(savedFile.getFilePath() + "/" + savedFile.getFileName());
        }

        categoryRepo.update(category);
        category = new Category();
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
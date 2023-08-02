package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.model.File;
import com.souraj.foodorder.repository.CategoryRepo;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

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
        fileUploadController.clearUploadedFiles();
    }

    public void beforeUpdate(Category ctg) {
        this.category = categoryRepo.findById(ctg.getId());
    }

    public void addCategory() {
        if (!fileUploadController.getUploadedFiles().isEmpty()) {
            File savedFile = fileUploadController.getUploadedFiles().get(0);
            category.setFilePath(savedFile.getFilePath());
        }

        categoryRepo.save(category);
        category = new Category();
        fileUploadController.clearUploadedFiles();
    }

    public void update() {
        if (!fileUploadController.getUploadedFiles().isEmpty()) {
            if (category.getFilePath() != null && !category.getFilePath().isEmpty()) {
                fileUploadController.deleteFileByPath(category.getFilePath());
            }

            File savedFile = fileUploadController.getUploadedFiles().get(0);
            category.setFilePath(savedFile.getFilePath());
        }

        categoryRepo.update(category);
        category = new Category();
        fileUploadController.clearUploadedFiles();
    }

    public void deleteById(Long id) {
        categoryRepo.delete(id);
        loadData();
    }

    public void saveOrUpdateCategory() {
        if (category.getId() == null) {
            addCategory();
        } else {
            update();
        }
    }
    public void loadData() {
        this.categoryList = categoryRepo.findAll();
    }
}

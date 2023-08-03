package com.souraj.foodorder.controller;

import com.souraj.foodorder.controller.FileUploadController;
import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.repository.CategoryRepo;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
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
    }

    public void beforeUpdate(Category ctg) {
        this.category = categoryRepo.findById(ctg.getId());
    }

    public void saveOrUpdate() {
        fileUploadController.saveUploadedFile();

        String uploadedFilePath = fileUploadController.getUploadedFilePath();
        if (uploadedFilePath != null) {
            category.setFilePath(uploadedFilePath); // Set the file path in the category object
        }

        if (category.getId() == null) {
            categoryRepo.save(category);
        } else {
            categoryRepo.update(category);
        }

        category = new Category();
        fileUploadController.getUploadedFiles().clear();
    }

    public void deleteById(Long id) {
        Category categoryToDelete = categoryRepo.findById(id);
        String filePath = categoryToDelete.getFilePath();
        if (filePath != null) {
            fileUploadController.deleteFile(filePath);
        }
        categoryRepo.delete(id);
        loadData();
    }

    public void loadData() {
        this.categoryList = new ArrayList<>();
        this.categoryList = categoryRepo.findAll();
    }
}

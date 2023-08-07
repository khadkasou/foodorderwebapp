package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.repository.CategoryRepo;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "categoryController")
@SessionScoped
public class CategoryController implements Serializable {

    private Category category;
    private List<Category> categoryList;
    private UploadedFile uploadedFile;
    private Category selectedCategory;
    private String selectedFileContent;

    @Inject
    private CategoryRepo categoryRepo;

    @Inject
    private FileUploadController fileUploadController;

    public Category getCategory() {
        return category;
    }

    public String getSelectedFileContent() {
        return selectedFileContent;
    }

    public void setSelectedFileContent(String selectedFileContent) {
        this.selectedFileContent = selectedFileContent;
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

    public Category getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Category selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

//    public void listen(FileUploadEvent event) {
//        if (event != null) {
//            this.uploadedFile = event.getFile();
//        }
//    }
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

    public void listen(FileUploadEvent event) {
        uploadedFile = event.getFile();
    }

    public void saveOrUpdate() {
        fileUploadController.saveUploadedFile(uploadedFile);
        if (uploadedFile != null) {
            category.setFilePath(uploadedFile.getFileName());
        }

        if (category.getId() == null) {
            categoryRepo.save(category);

        } else {
            categoryRepo.update(category);
            uploadedFile = null;
        }
        category = new Category();
        loadData();
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

    public void viewFile(Category category) {
        selectedCategory = category;
        selectedFileContent = fileUploadController.viewFileContent(category.getFilePath());
    }

    public void loadData() {
        this.categoryList = categoryRepo.findAll();
    }

}
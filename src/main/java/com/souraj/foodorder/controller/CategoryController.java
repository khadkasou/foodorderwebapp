package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.repository.CategoryRepo;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import org.primefaces.context.PrimeFacesContext;
import org.primefaces.event.FileUploadEvent;

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

    public void listen(FileUploadEvent event) {
        System.out.println("EVENT" + event);
        if (event != null) {
            uploadedFile = event.getFile();
        }
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
        System.out.println("HELLO HELLO SAVE TRIGGERED");
        System.out.print(" SAVE TRIGGERED");

//        UploadedFile uploadedFiles = fileUploadController.getUploadedFile();
//        System.out.println("uploadedFiles :"+uploadedFiles);
//        if (!uploadedFiles.isEmpty()) {
//            UploadedFile uploadedFile = uploadedFiles.get(0);
        String filePath = fileUploadController.saveUploadedFile(uploadedFile);
        category.setFilePath(filePath);
//        }

        if (category.getId() == null) {
//            categoryRepo.save(category);
        } else {
            categoryRepo.update(category);
        }
        fileUploadController.getUploadedFiles().clear();
        category = new Category();
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
        this.categoryList = categoryRepo.findAll();
    }

    public void viewFile(Category category) {
        fileUploadController.viewFile(category.getFilePath());
    }

}

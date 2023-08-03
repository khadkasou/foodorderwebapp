package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.repository.CategoryRepo;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.event.FileUploadEvent;

@ManagedBean
@SessionScoped
public class CategoryController implements Serializable {

    private Category category;
    private List<Category> categoryList;
    private UploadedFile uploadedFile;

    @EJB
    private CategoryRepo categoryRepo;
    
    @Inject
    private FileUploadController fileUploadController;
            
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
            this.uploadedFile = event.getFile();
        }
    }

    @PostConstruct
    public void init() {
        System.out.println("INIT");
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

        String filePath = fileUploadController.saveUploadedFile(uploadedFile);
        if(uploadedFile != null){
             category.setFilePath(uploadedFile.getFileName());
        }
       

        if (category.getId() == null) {
            categoryRepo.save(category);
        } else {
            categoryRepo.update(category);
        }
        category = new Category();
        loadData();
    }

    public void deleteById(Long id) {
        Category categoryToDelete = categoryRepo.findById(id);
        String filePath = categoryToDelete.getFilePath();
        if (filePath != null) {
            categoryRepo.deleteFile(filePath);
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

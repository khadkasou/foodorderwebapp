package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.repository.CategoryRepo;
import java.io.IOException;
import java.io.InputStream;
import org.primefaces.model.UploadedFile;
import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "categoryController")
@SessionScoped
public class CategoryController implements Serializable {

    private Category category;
    private List<Category> categoryList;
    private UploadedFile uploadedFile;
    private Category selectedCategory;
    private String selectedFileContent;
    byte[] file;
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
        uploadedFile = null;
    }

    public void saveUploadedFile(FileUploadEvent event) throws IOException {
        this.uploadedFile = event.getFile();
        InputStream inputs = event.getFile().getInputstream();
        this.file = IOUtils.toByteArray(inputs);

    }

    public void saveData() {
        if (this.file != null) {

            try {
                String uploadFolderPath = "/home/ksouraj/Uploads/";
                Path folderPath = Paths.get(uploadFolderPath);
                Path destinationPath = folderPath.resolve(uploadedFile.getFileName());
                Files.write(destinationPath, file);
                if (uploadedFile != null) {
                    category.setFilePath(uploadedFile.getFileName());

                    categoryRepo.save(category);
                }
            } catch (IOException e) {
                e.printStackTrace();
                FacesMessage message = new FacesMessage("Error",
                        "Error while uploading the file.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }

        } else {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Invalid file type.",
                    "Please upload .pdf or .jpg files only.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
        uploadedFile = null;
        category = new Category();
        loadData();

    }

    public boolean isFileTypeAllowed(UploadedFile uploadedFile) {
        String fileName = uploadedFile.getFileName();
        if (fileName != null) {
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
            return "jpg".equalsIgnoreCase(extension) || "pdf".equalsIgnoreCase(extension);
        }
        return false;
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

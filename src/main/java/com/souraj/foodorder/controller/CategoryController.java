package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.repository.CategoryRepo;
import java.io.FileInputStream;
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
    byte[] selectedFileContent;

    byte[] file;
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

    public byte[] getSelectedFileContent() {
        return selectedFileContent;
    }

    public void setSelectedFileContent(byte[] selectedFileContent) {
        this.selectedFileContent = selectedFileContent;
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
        uploadedFile = null;
    }

    public void saveUploadedFile(FileUploadEvent event) throws IOException {
        this.uploadedFile = event.getFile();
        InputStream inputs = event.getFile().getInputstream();
        this.file = IOUtils.toByteArray(inputs);

    }

    public void saveData() {
        if (this.file != null) {
            long fileSize = uploadedFile.getSize();

            if (fileSize > 1000000) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "File size exceeds the limit (1MB).", null);
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }

            try {
                String uploadFolderPath = "/home/ksouraj/Uploads/";
                Path folderPath = Paths.get(uploadFolderPath);
                Path destinationPath = folderPath.resolve(uploadedFile.getFileName());
                Files.write(destinationPath, file);

                if (uploadedFile != null) {
                    category.setFilePath(uploadedFile.getFileName());
                }
            } catch (IOException e) {
                // Error handling for file upload
                e.printStackTrace();
                FacesMessage message = new FacesMessage("Error",
                        "Error while uploading the file.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
        }

        try {
            if (category.getId() == null) {
                categoryRepo.save(category);
            } else {
                category = categoryRepo.findById(category.getId());
                categoryRepo.update(category);
            }

            FacesMessage successMessage = new FacesMessage("Category Saved Successfully");
            FacesContext.getCurrentInstance().addMessage(null, successMessage);
        } catch (Exception e) {

            e.printStackTrace();
            FacesMessage message = new FacesMessage("Error",
                    "Error while saving the category.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        // Clear uploaded file and reset category
        uploadedFile = null;
        category = new Category();

        // Load data
        loadData();
    }

//    public void saveData() {
//        if (this.file != null) {
//            long fileSize = uploadedFile.getSize();
//
//            if (fileSize > 1000000) {
//                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                        "File size exceeds the limit (1MB).", null);
//                FacesContext.getCurrentInstance().addMessage(null, message);
//                return;
//            }
//
//            try {
//                String uploadFolderPath = "/home/ksouraj/Uploads/";
//                Path folderPath = Paths.get(uploadFolderPath);
//                Path destinationPath = folderPath.resolve(uploadedFile.getFileName());
//                Files.write(destinationPath, file);
//                if (uploadedFile != null) {
//                    category.setFilePath(uploadedFile.getFileName());
//                }
//                if (category.getId() == null) {
//                    categoryRepo.save(category);
//
//                } else {
//                    categoryRepo.update(category);
//
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//                FacesMessage message = new FacesMessage("Error",
//                        "Error while uploading the file.");
//                FacesContext.getCurrentInstance().addMessage(null, message);
//            }
//
//        } else {
//            FacesMessage message = new FacesMessage(
//                    FacesMessage.SEVERITY_ERROR, "Invalid file type.",
//                    "Please upload .pdf or .jpg files only.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//        FacesMessage successMessage = new FacesMessage("File Uploaded Successfully");
//        FacesContext.getCurrentInstance().addMessage(null, successMessage);
//
//        RequestContext.getCurrentInstance().update("frm_show_category:growl");
//        uploadedFile = null;
//        category = new Category();
//        loadData();
//
//    }
    public boolean isFileTypeAllowed(UploadedFile uploadedFile) {
        String fileName = uploadedFile.getFileName();
        if (fileName != null) {
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
            return "jpg".equalsIgnoreCase(extension) || "pdf".equalsIgnoreCase(extension);
        }
        return false;
    }

 public void viewFile(String filePath) {
    if (filePath != null && !filePath.isEmpty()) {
        try {
            String uploadFolderPath = "/home/ksouraj/Uploads/";
            String fileFullPath = uploadFolderPath + filePath;

            InputStream inputStream = new FileInputStream(fileFullPath);
            selectedFileContent = IOUtils.toByteArray(inputStream);

            inputStream.close();
        } catch (IOException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error reading file", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, message);
            selectedFileContent = null;
        }
    } else {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "File path is empty", null);
        FacesContext.getCurrentInstance().addMessage(null, message);
        selectedFileContent = null;
    }
}

    
       public boolean isImageFile(byte[] fileContent) {
        if (fileContent != null) {
            String extension = getFileExtension(fileContent);
            return "jpg".equalsIgnoreCase(extension) || "jpeg".equalsIgnoreCase(extension) || "png".equalsIgnoreCase(extension);
        }
        return false;
    }
       
        public boolean isPdfFile(byte[] fileContent) {
        if (fileContent != null) {
            String extension = getFileExtension(fileContent);
            return "pdf".equalsIgnoreCase(extension);
        }
        return false;
    }
    
    
    public String getFileExtension(byte[] fileContent) {
    if (isImageFile(fileContent)) {
        return "jpg"; 
    } else if (isPdfFile(fileContent)) {
        return "pdf";
    } else {
        
        return "";
    }
}

    
    
    
        public void deleteById(Long id) {
        categoryRepo.delete(id);
        loadData();
    }

    
    
    

    public void loadData() {
        this.categoryList = categoryRepo.findAll();
    }

   

}

package com.souraj.foodorder.controller;


import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.repository.CategoryRepo;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;

@ManagedBean(name = "fileUploadsController")
@SessionScoped
public class OldFileUploadController implements Serializable {

    private List<UploadedFile> uploadedFiles;
    private StreamedContent streamedContent;
    private UploadedFile uploadedFile;
    private String streamedContents;
    
    
     private  Category category;
     
     @Inject
     private CategoryRepo categoryRepo;

    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }

    @PostConstruct
    public void init() {
        uploadedFiles = new ArrayList<>();
        
    }

    public List<UploadedFile> getUploadedFiles() {
        return uploadedFiles;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getStreamedContents() {
        return streamedContents;
    }

    public void setStreamedContents(String streamedContents) {
        this.streamedContents = streamedContents;
    }

    
    
     
      


    public void deleteFile(String filePath) {
        try {
            if (filePath.equals(streamedContent)) {
                streamedContent = null;
            }
            Path fileToDelete = Paths.get(filePath);
            Files.deleteIfExists(fileToDelete);
        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Error", "Error while deleting the file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public String viewFileContent(String filePath) {
        try {
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            byte[] fileBytes = IOUtils.toByteArray(inputStream);
            String base64Encoded = Base64.getEncoder().encodeToString(fileBytes);
            String contentType = Files.probeContentType(Paths.get(filePath));
            return "data:" + contentType + ";base64," + base64Encoded;
        } catch (IOException e) {
            FacesMessage message = new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, "Error", "Error while viewing the file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return null;
        }
    }

    public void uploadFiles() {

        uploadedFiles.clear();
    }
    
}

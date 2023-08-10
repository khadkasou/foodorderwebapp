/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.FileName;
import com.souraj.foodorder.repository.FileNameRepository;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ksouraj
 */
@ManagedBean(name = "fileNameController")
@SessionScoped
public class FileNameController implements Serializable {

    private FileName fileName;
    private List<FileName> fileNameList;
    private UploadedFile uploadedFile;
    byte[] selectedFileContent;
    byte[] file;
    
    
    @Inject
    private FileNameRepository fileNameRepository;
    
    

    public FileName getFileName() {
        return fileName;
    }

    public void setFileName(FileName fileName) {
        this.fileName = fileName;
    }

    public List<FileName> getFileNameList() {
        return fileNameList;
    }

    public void setFileNameList(List<FileName> fileNameList) {
        this.fileNameList = fileNameList;
    }

  

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public byte[] getSelectedFileContent() {
        return selectedFileContent;
    }

    public void setSelectedFileContent(byte[] selectedFileContent) {
        this.selectedFileContent = selectedFileContent;
    }

 

    @PostConstruct
    public void init(){
        this.fileName = new FileName();
    }
  
    
    public void beforeCreate(){
        this.fileName = new FileName();
    }
    
    public void beforeUpdate(FileName fl){
        this.fileName =fileNameRepository.findById(fl.getId());
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
                     fileName.setName(uploadedFile.getFileName());
                     fileName.setFsize(uploadedFile.getSize());
                     
                }
            } catch (IOException e) {
                e.printStackTrace();
                FacesMessage message = new FacesMessage("Error",
                        "Error while uploading the file.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
        }

        try {
            if (fileName.getId() == null) {
                fileNameRepository.save(fileName);
            } else {
                fileName = fileNameRepository.findById(fileName.getId());
                fileNameRepository.update(fileName);
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
        uploadedFile = null;
        fileName = new FileName();
        loadData();
    }
      
    
        
        
        
        
      public void loadData() {
        this.fileNameList = fileNameRepository.findAll();
    }

}

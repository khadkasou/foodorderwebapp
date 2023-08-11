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
import java.util.Arrays;
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
    public void init() {
        this.fileName = new FileName();
        loadData();
    }

    public void beforeCreate() {
        this.fileName = new FileName();
    }

    public void beforeUpdate(FileName fl) {
        this.fileName = fileNameRepository.findById(fl.getId());
    }

   public void saveUploadedFile(FileUploadEvent event) throws IOException {
        this.uploadedFile = event.getFile();
        if (!validateFileType(uploadedFile) || !validateFileSize(uploadedFile)) {
            return;
        }
        InputStream inputs = event.getFile().getInputstream();
        this.file = IOUtils.toByteArray(inputs);
    }

    public void saveData() {
        if (this.file != null) {

            try {
                String uploadFolderPath = "/home/ksouraj/uploaded/Files/";
                Path folderPath = Paths.get(uploadFolderPath);
                Path destinationPath = folderPath.resolve(uploadedFile.getFileName());
                Files.write(destinationPath, file);
                fileName.setLocation(destinationPath.toString());
                fileName.setAllowedType(getFileExtension(fileName.getName()).toLowerCase());

                if (uploadedFile != null) {
                    fileName.setName(uploadedFile.getFileName());
                    fileName.setFsize(uploadedFile.getSize());
                    fileName.setLocation(uploadFolderPath);

                    fileNameRepository.save(fileName);

                }
            } catch (IOException e) {
                FacesMessage message = new FacesMessage("Error",
                        "Error while uploading the file.");
                FacesContext.getCurrentInstance().addMessage(null, message);
                return;
            }
        }

        
        uploadedFile = null;
        fileName = new FileName();
        loadData();
    }

   public boolean validateFileType(UploadedFile uploadedFile) {
        String f = uploadedFile.getFileName();
        String extension = f.substring(f.lastIndexOf(".")).toLowerCase();
        List<String> allowedFileTypes = Arrays.asList(".jpg", ".png", ".jpeg", ".pdf", ".xls");
        if (!allowedFileTypes.contains(extension)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid file type. Only JPG, PNG, JPEG, PDF, and XLS files are allowed.", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        return true;
    }

    public boolean validateFileSize(UploadedFile uploadedFile) {
        long fileSize = uploadedFile.getSize();
        long maxSize = 0;
        String extension = getFileExtension(uploadedFile.getFileName());

        switch (extension) {
            case "jpg":
            case "png":
            case "jpeg":
                maxSize = 1000;
                break;
            case "pdf":
                maxSize = 2000000;
                break;
            case "xls":
                maxSize = 3000000;
                break;
            default:
                break;
        }

        if (fileSize > maxSize) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "File size exceeds the limit.", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        return true;
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public void loadData() {
        this.fileNameList = fileNameRepository.findAll();
    }

}

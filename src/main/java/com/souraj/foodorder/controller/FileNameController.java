/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.FileName;
import com.souraj.foodorder.repository.FileNameRepository;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ksouraj
 */
@Named(value = "fileNameController")
@ViewScoped
public class FileNameController implements Serializable {

    private FileName fileName;
    private List<FileName> fileNameList;
    private UploadedFile uploadedFile;

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

    public void fileUploadHandler(FileUploadEvent event) throws IOException {
        uploadedFile = event.getFile();
        if (!validateFileType(uploadedFile) || !validateFileSize(uploadedFile)) {
            return;
        }

        try {
            String uploadFolderPath = "/home/ksouraj/Uploads/Files/";
            Path folderPath = Paths.get(uploadFolderPath);
            Path destinationPath = folderPath.resolve(uploadedFile.getFileName());
            byte[] fileBytes = IOUtils.toByteArray(uploadedFile.getInputstream());

            Files.write(destinationPath, fileBytes);
            //       String fileExtension = getFileExtension(uploadedFile.getFileName()).toLowerCase();

            this.fileName.setName(fileName.getName());
            this.fileName.setLocation(folderPath.toString());
            this.fileName.setLocation(destinationPath.toString());
   //         this.fileName.setAllowedType(fileExtension);
            int fileSize = uploadedFile.getInputstream().available();
            this.fileName.setFsize(fileSize);

        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Error", "Error while uploading the file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        loadData();
    }

    public void saveFile() {
        fileNameRepository.save(this.fileName);
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
                maxSize = 1000000;
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

    public String getFileExtension(String fileName) {
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex >= 0) {
            return fileName.substring(lastDotIndex + 1);
        }
        return "";
    }

    public void loadData() {
        this.fileNameList = fileNameRepository.findAll();
    }

}

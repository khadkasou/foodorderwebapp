/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Configuration;
import com.souraj.foodorder.model.FileUpload;
import com.souraj.foodorder.repository.ConfigurationRepository;
import com.souraj.foodorder.repository.FileUploadRepository;
import java.io.IOException;
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
@ManagedBean(name = "fileUploadController")
@SessionScoped
public class FlileUploadController implements Serializable {

    private FileUpload fileUpload;
    private List<FileUpload> fileUploadList;
    private UploadedFile uploadedFile;
    private Configuration configuration;

    @Inject
    private FileUploadRepository fileUploadRepository;

    @Inject
    private ConfigurationRepository configurationRepository;

    public FlileUploadController() {
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    public List<FileUpload> getFileUploadList() {
        return fileUploadList;
    }

    public void setFileUploadList(List<FileUpload> fileUploadList) {
        this.fileUploadList = fileUploadList;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    @PostConstruct
    public void init() {
        this.fileUpload = new FileUpload();
        loadData();
    }

    public void beforeCreate() {
        this.fileUpload = new FileUpload();
    }

    public void beforeUpdate(FileUpload fu) {
        this.fileUpload = fileUploadRepository.findById(fu.getId());

    }

    public void fileUploadHandler(FileUploadEvent event) throws IOException {
        System.out.println("helloooooooo..");
        uploadedFile = event.getFile();
        try {
            String uploadFolderPath = "/home/ksouraj/Uploads/Files/";
            Path folderPath = Paths.get(uploadFolderPath);
            Path destinationPath = folderPath.resolve(uploadedFile.getFileName());
            byte[] fileBytes = IOUtils.toByteArray(uploadedFile.getInputstream());

            Files.write(destinationPath, fileBytes);
            //       String fileExtension = getFileExtension(uploadedFile.getFileName()).toLowerCase();

            this.fileUpload.setFileNameId(fileUpload.getFileNameId());
            this.fileUpload.setCreatedBy(fileUpload.getCreatedBy());
            this.fileUpload.setRemarks(fileUpload.getRemarks());
            this.fileUpload.setfName(uploadedFile.getFileName());
            this.fileUpload.setLocation(destinationPath.toString());

        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Error", "Error while uploading the file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
            return;
        }

        loadData();
    }

    public void saveFile() {
        List<Configuration> conList = configurationRepository.findAll();
        for (Configuration config : conList) {
            List<String> extensiosn = Arrays.asList(config.getAllowedType().split(","));
            if (config.getName().equals(uploadedFile.getFileName())) {
                if (validateFileSize(uploadedFile, config.getFsize())
                        && validateFileType(uploadedFile, extensiosn)) {

                    fileUploadRepository.save(this.fileUpload);
                    loadData();
                }
            }
        }
    }

    public boolean validateFileType(UploadedFile uploadedFile, List<String> allowedFileTypes) {
        String f = uploadedFile.getFileName();
        String extension = f.substring(f.lastIndexOf(".")).toLowerCase();
        if (!allowedFileTypes.contains(extension)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid file type. Only JPG, PNG, JPEG, PDF, and XLS files are allowed.", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        return true;
    }

    public boolean validateFileSize(UploadedFile uploadedFile, Integer limit) {
        long fileSize = uploadedFile.getSize();
        long maxSize = limit.longValue();

        if (fileSize > maxSize) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "File size exceeds the limit.", null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            return false;
        }
        return true;
    }

    public void loadData() {
        this.fileUploadList = fileUploadRepository.findAll();
    }

}

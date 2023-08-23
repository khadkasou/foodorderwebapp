/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Configuration;
import com.souraj.foodorder.model.FileUpload;
import static com.souraj.foodorder.model.FileUpload_.remarks;
import com.souraj.foodorder.repository.ConfigurationRepository;
import com.souraj.foodorder.repository.FileUploadRepository;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ksouraj
 */
@Named(value = "fileUploadController")
@ViewScoped
public class FileUploadController implements Serializable {

    private FileUpload fileUploads;
    private List<FileUpload> fileUploadList;
    private UploadedFile uploadedFile;
    private Configuration configs;
    private List<Configuration> configurationList;
    byte[] file;
    private byte[] viewedFileData;

    @Inject
    private FileUploadRepository fileUploadRepository;

    @Inject
    private ConfigurationRepository configurationRepository;

    public byte[] getViewedFileData() {
        return viewedFileData;
    }

    public void setViewedFileData(byte[] viewedFileData) {
        this.viewedFileData = viewedFileData;
    }

    public Configuration getConfigs() {
        return configs;
    }

    public void setConfigs(Configuration configs) {
        this.configs = configs;
    }

    public FileUploadController() {
    }

    public List<Configuration> getConfigurationList() {
        return configurationList;
    }

    public void setConfigurationList(List<Configuration> configurationList) {
        this.configurationList = configurationList;
    }

    public FileUpload getFileUploads() {
        return fileUploads;
    }

    public void setFileUploads(FileUpload fileUploads) {
        this.fileUploads = fileUploads;
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
        this.fileUploads = new FileUpload();
        configs = new Configuration();
        this.configurationList = configurationRepository.findAll();

        loadData();
    }

    public void beforeCreate() {
        this.fileUploads = new FileUpload();
        this.configurationList = new ArrayList<>();
    }

    public void beforeUpdate(FileUpload fu) {
        this.fileUploads = fileUploadRepository.findById(fu.getId());

    }

    public void fileUploadHandler(FileUploadEvent event) throws IOException {
        uploadedFile = event.getFile();
        this.file = IOUtils.toByteArray(uploadedFile.getInputstream());

    }

    public void saveFile() {
        List<Configuration> conList = configurationRepository.findAll();
        for (Configuration config : conList) {
            List<String> extensions = Arrays.asList(config.getAllowedTypes().split(","));
            if (config.getId() == fileUploads.getConfiguration().getId()) {
                if (validateFileSize(uploadedFile, config.getFsize())
                        && validateFileType(uploadedFile, extensions)) {
                    try {
                        String uploadFolderPath = "/home/synergy/Uploads/Files/";
                        Path folderPath = Paths.get(uploadFolderPath);
                        Path destinationPath = folderPath.resolve(uploadedFile.getFileName());
                        Files.write(destinationPath, this.file);
                        this.fileUploads.setfName(uploadedFile.getFileName());
                        this.fileUploads.setLocation(destinationPath.toString());

                        fileUploadRepository.save(this.fileUploads);
                        loadData();
                    } catch (Exception e) {
                        e.printStackTrace();
                        FacesMessage message = new FacesMessage("Error", "Error while uploading the file.");
                        FacesContext.getCurrentInstance().addMessage(null, message);
                        return;

                    }
                }
            }
        }
    }

    public boolean validateFileType(UploadedFile uploadedFile, List<String> allowedFileTypes) {
        String f = uploadedFile.getFileName();
        String extension = f.substring(f.lastIndexOf(".")).toLowerCase();

        if (!allowedFileTypes.contains(extension)) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid file type.", null);
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

    public void delete(Long id) {
        fileUploadRepository.delete(id);
        loadData();
    }

    public StreamedContent getImageForFile() throws IOException {
        FacesContext context = FacesContext.getCurrentInstance();

        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {

            String filePath = uploadedFile.getFileName();
            String uploadFolderPath = "/home/synergy/Uploads/Files/" + filePath;
            Path folderPath = Paths.get(uploadFolderPath);
            File file = new File(filePath);
            InputStream inputStream = new FileInputStream(file);
            byte[] bytes = Files.readAllBytes(file.toPath());
            return new DefaultStreamedContent(new ByteArrayInputStream(bytes), "*/*");
            
            
//            String fileID = context.getExternalContext().getRequestParameterMap().get("fileid");
//
//            if (fileID != null && !fileID.isEmpty()) {
//                FileUpload fileUpload = fileUploadRepository.findById(Long.valueOf(fileID));
//
//                if (fileUpload != null) {
//                    String filePath = fileUpload.getLocation(); 
//                    File file = new File(filePath);
//                    InputStream inputStream = new FileInputStream(file);
//                    byte[] bytes = Files.readAllBytes(file.toPath());
//                    setViewedFileData(bytes); 
//                    return new DefaultStreamedContent(new ByteArrayInputStream(bytes), "*/*");
//                }
//            }
        }
    }

    public void loadData() {
        this.fileUploadList = fileUploadRepository.findAll();
    }

}

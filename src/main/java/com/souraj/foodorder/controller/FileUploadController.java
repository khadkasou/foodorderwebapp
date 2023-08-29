/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Configuration;
import com.souraj.foodorder.model.FileUpload;
import com.souraj.foodorder.repository.ConfigurationRepository;
import com.souraj.foodorder.repository.FileUploadRepository;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.io.IOUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
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
    private Configuration selectedConfiguration;
    private List<Configuration> configurationList;
    byte[] file;
    private byte[] viewedFileData;
    private StreamedContent viewedFileContent;
    private InputStream inputStream;

    private StreamedContent streamContent;
    
    private final String basePath ="/home/synergy/Uploads/";

    @Inject
    private FileUploadRepository fileUploadRepository;

    @Inject
    private ConfigurationRepository configurationRepository;

    public StreamedContent getStreamContent() {
        return streamContent;
    }

    public void setStreamContent(StreamedContent streamContent) {
        this.streamContent = streamContent;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public StreamedContent getViewedFileContent() {
        return viewedFileContent;
    }

    public byte[] getViewedFileData() {
        return viewedFileData;
    }

    public void setViewedFileData(byte[] viewedFileData) {
        this.viewedFileData = viewedFileData;
    }

    public Configuration getConfigs() {
        return selectedConfiguration;
    }

    public void setConfigs(Configuration configs) {
        this.selectedConfiguration = configs;
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
        selectedConfiguration = new Configuration();
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

//    public void saveFile() {
//        List<Configuration> conList = configurationRepository.findAll();
//        for (Configuration config : conList) {
//            List<String> extensions = Arrays.asList(config.getAllowedTypes().split(","));
//            if (config.getId() == fileUploads.getConfiguration().getId()) {
//                if (validateFileSize(uploadedFile, config.getFsize())
//                        && validateFileType(uploadedFile, extensions)) {
//                    try {
//                        String uploadFolderPath = "/home/synergy/Uploads/Files/";
//                        Path folderPath = Paths.get(uploadFolderPath);
//                        Path destinationPath = folderPath.resolve(uploadedFile.getFileName());
//                        Files.write(destinationPath, this.file);
//                        this.fileUploads.setfName(uploadedFile.getFileName());
//                        this.fileUploads.setLocation(destinationPath.toString());
//
//                        fileUploadRepository.save(this.fileUploads);
//                        loadData();
//                    } catch (IOException e) {
//                        addErrorMessage("Error while uploading the file.");
//                        return;
//
//                    }
//                }
//            }
//        }
//    }
    
    
//    public void saveFile() {
//    List<Configuration> conList = configurationRepository.findAll();
//    for (Configuration config : conList) {
//        List<String> extensions = Arrays.asList(config.getAllowedTypes().split(","));
//        if (config.getId() == fileUploads.getConfiguration().getId()) {
//            if (validateFileSize(uploadedFile, config.getFsize())
//                    && validateFileType(uploadedFile, extensions)) {
//                try {
//                    String uploadFolderPath = basePath + "Files/";
//                    Path folderPath = Paths.get(uploadFolderPath);
//                    Path destinationPath = folderPath.resolve(uploadedFile.getFileName());
//
//                    
//                    Path relativePath = Paths.get("Files", uploadedFile.getFileName());
//                    Files.write(destinationPath, this.file);
//                    this.fileUploads.setfName(uploadedFile.getFileName());
//                    this.fileUploads.setLocation(relativePath.toString()); // Save relative path
//
//                    fileUploadRepository.save(this.fileUploads);
//                    loadData();
//                } catch (IOException e) {
//                    addErrorMessage("Error while uploading the file.");
//                    return;
//                }
//            }
//        }
//    }
//}
    
    
//public void saveFile() {
//    List<Configuration> conList = configurationRepository.findAll();
//    for (Configuration config : conList) {
//        List<String> extensions = Arrays.asList(config.getAllowedTypes().split(","));
//        if (config.getId() == fileUploads.getConfiguration().getId()) {
//            if (validateFileSize(uploadedFile, config.getFsize())
//                    && validateFileType(uploadedFile, extensions)) {
//                try {
//                    
//                    String fileType = determineFileType(uploadedFile);
//                    String uploadDate = getFormattedUploadDate();
//
//                    String uploadFolderPath = basePath + fileType + "/" + uploadDate + "/";
//                    Path folderPath = Paths.get(uploadFolderPath);
//
//                    if (!Files.exists(folderPath)) {
//                        Files.createDirectories(folderPath);
//                    }
//
//                    
//                    Path destinationPath = folderPath.resolve(uploadedFile.getFileName());
//
//                    
//                    Path relativePath = Paths.get(fileType, uploadDate, uploadedFile.getFileName());
//                    Files.write(destinationPath, this.file);
//                    this.fileUploads.setfName(uploadedFile.getFileName());
//                    this.fileUploads.setLocation(relativePath.toString()); 
//
//                    fileUploadRepository.save(this.fileUploads);
//                    loadData();
//                } catch (IOException e) {
//                    addErrorMessage("Error while uploading the file.");
//                    return;
//                }
//            }
//        }
//    }
//}

//private String determineFileType(UploadedFile uploadedFile) {
//    String fileName = uploadedFile.getFileName();
//    if (fileName.toLowerCase().contains("citizenship")) {
//        return "Citizenship";
//    }
//   
//    return "Other"; 
//}
//
//private String getFormattedUploadDate() {
//    LocalDate uploadDate = LocalDate.now(); 
//
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//    return uploadDate.format(formatter);
//}


    

    public boolean validateFileType(UploadedFile uploadedFile, List<String> allowedFileTypes) {
        String f = uploadedFile.getFileName();
        String extension = f.substring(f.lastIndexOf(".")).toLowerCase();

        if (!allowedFileTypes.contains(extension)) {
            addErrorMessage("Invalid file type.");

            return false;
        }
        return true;
    }

    public boolean validateFileSize(UploadedFile uploadedFile, Integer limit) {
        long fileSize = uploadedFile.getSize();
        long maxSize = limit.longValue();

        if (fileSize > maxSize) {
            addErrorMessage("File size exceeds the limit.");
            return false;
        }
        return true;
    }

    public void delete(Long id) {
        fileUploadRepository.delete(id);
        loadData();
    }

    public void loadFileData(Long id) throws FileNotFoundException, IOException {
        this.fileUploads = new FileUpload();
        if (id != null) {
            fileUploads = fileUploadRepository.findById(id);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('viewDocDlg').show();");
        }
    }
    
    public boolean isImage() {
        if (fileUploads != null) {
            String fileName = fileUploads.getfName();
            return fileName.toLowerCase().endsWith(".jpg") || fileName.toLowerCase().endsWith(".png");
        }
        return false;
    }

    private void addErrorMessage(String message) {
        FacesMessage errorMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
        FacesContext.getCurrentInstance().addMessage(null, errorMessage);
    }

    public void loadData() {
        this.fileUploadList = fileUploadRepository.findAll();
    }

}

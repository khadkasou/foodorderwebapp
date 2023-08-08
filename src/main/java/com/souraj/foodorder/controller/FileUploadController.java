package com.souraj.foodorder.controller;

import com.souraj.foodorder.exceptions.CustomException;
import com.souraj.foodorder.model.Category;
import java.io.InputStream;
import java.io.Serializable;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.io.IOUtils;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

@ManagedBean(name = "fileUploadController")
@SessionScoped
public class FileUploadController implements Serializable {

    private List<UploadedFile> uploadedFiles;
    private StreamedContent streamedContent;
    private UploadedFile uploadedFile;
    private String streamedContents;
    private Category category;

    public FileUploadController() {
        uploadedFiles = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        uploadedFiles = new ArrayList<>();
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String saveFileToFolder(InputStream input, String fileName) throws IOException {
        String uploadFolderPath = "/home/ksouraj/Uploads";

        if (uploadFolderPath == null || uploadFolderPath.isEmpty()) {
            throw new CustomException("Please configure the file path");
        }

        byte[] fileBytes = IOUtils.toByteArray(input);

        Path fileToSave = Paths.get(uploadFolderPath, fileName);
        Files.write(fileToSave, fileBytes);

        return fileName;
    }

    public void saveUploadedFile(FileUploadEvent event) {
        if (event != null && event.getFile() != null) {
            uploadedFile = event.getFile();
            String fileName = uploadedFile.getFileName();

            try {
                InputStream input = uploadedFile.getInputstream();
                String filePath = saveFileToFolder(input, fileName);

                category.setFilePath(filePath);
                category.setFileName(fileName);
            } catch (IOException e) {
                FacesMessage message = new FacesMessage(
                        FacesMessage.SEVERITY_ERROR, "Error", "Error while saving file.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public boolean isFileTypeAllowed(UploadedFile uploadedFile) {
        String fileName = uploadedFile.getFileName();
        if (fileName != null) {
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1);

            if (("jpg".equals(extension)) || ("pdf".equals(extension))) {
                return true;
            }

        }
        return false;
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

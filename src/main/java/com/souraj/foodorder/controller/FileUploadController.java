
package com.souraj.foodorder.controller;

import com.souraj.foodorder.repository.FileRepository;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultUploadedFile;

@ManagedBean(name = "fileUploadController")
@SessionScoped
public class FileUploadController implements Serializable {

    private List<UploadedFile> uploadedFiles;
    private StreamedContent streamedContent;
    private UploadedFile uploadedFile;

    @Inject
    private FileRepository fileRepository;

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


   public String saveUploadedFile(UploadedFile uploadedFile) {
        System.out.println("uploadedFile :" + uploadedFile);
        InputStream input = null;
        if (uploadedFile != null) {
            try {
                String uploadFolderPath = "/home/ksouraj/Uploads";
                Path folderPath = Paths.get(uploadFolderPath);
                Files.createDirectories(folderPath);
                System.out.println("uploadFolderPath"+uploadFolderPath);
                
                System.out.println("UploadedFile"+uploadedFile.getFileName());
                OutputStream output = new FileOutputStream(new File(uploadFolderPath, uploadedFile.getFileName()));
                System.out.println("errorrrroooooo");
               
                System.out.println("okkkkkkkk"+input);
                
                IOUtils.copy(uploadedFile.getInputstream(), output);
                System.out.println("Completed");
                return "/Uploads/" + uploadedFile.getFileName();
            } catch (IOException e) {
                
                FacesMessage message = new FacesMessage("Error", "Error while uploading the file.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
        return null;
    }
   
    public void deleteFile(String filePath) {
        try {
            Path fileToDelete = Paths.get(filePath);
            Files.deleteIfExists(fileToDelete);
        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Error", "Error while deleting the file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void viewFile(String filePath) {
        try {
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            String contentType = Files.probeContentType(Paths.get(filePath));
            streamedContent = new DefaultStreamedContent(inputStream, contentType);
        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Error", "Error while viewing the file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void uploadFiles() {

        uploadedFiles.clear();
    }
}

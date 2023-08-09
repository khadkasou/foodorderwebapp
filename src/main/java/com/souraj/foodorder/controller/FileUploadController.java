package com.souraj.foodorder.controller;

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
import org.apache.commons.io.IOUtils;

@ManagedBean(name = "fileUploadController")
@SessionScoped
public class FileUploadController implements Serializable {

    private List<UploadedFile> uploadedFiles;
    private StreamedContent streamedContent;
    private UploadedFile uploadedFile;
    private String streamedContents;

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

//    public String saveUploadedFile(UploadedFile uploadedFile) {
//        if (uploadedFile != null && isFileTypeAllowed(uploadedFile)) {
//
//            try {
//                String uploadFolderPath = "/home/ksouraj/Uploads";
//                Path folderPath = Paths.get(uploadFolderPath);
//                Files.createDirectories(folderPath);
//
//                OutputStream output = new FileOutputStream(new File(uploadFolderPath, uploadedFile.getFileName()));
//                IOUtils.copy(uploadedFile.getInputstream(), output);
//
//                return "/Uploads/" + uploadedFile.getFileName();
//            } catch (IOException e) {
//
//                FacesMessage message = new FacesMessage("Error", "Error while uploading the file.");
//                FacesContext.getCurrentInstance().addMessage(null, message);
//            }
//
//        } else {
//            FacesMessage message = new FacesMessage(
//                    FacesMessage.SEVERITY_ERROR, "Invalid file type.", "Please upload .pdf or .jpg files only.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//
//        return null;
//    }
    
    public String saveUploadedFile(UploadedFile uploadedFile) {
    if (uploadedFile != null && isFileTypeAllowed(uploadedFile)) {
        try {
            String uploadFolderPath = "/home/ksouraj/Uploads";
            Path folderPath = Paths.get(uploadFolderPath);
            Files.createDirectories(folderPath);

            String fileName = uploadedFile.getFileName();
            Path filePath = folderPath.resolve(fileName);

            try (OutputStream output = new BufferedOutputStream(new FileOutputStream(filePath.toFile()));
                 InputStream input = new BufferedInputStream(uploadedFile.getInputstream())) {

                IOUtils.copy(input, output);
            }

            return "/Uploads/" + fileName;
        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Error", "Error while uploading the file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    } else {
        FacesMessage message = new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Invalid file type.", "Please upload .pdf or .jpg files only.");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    return null;
}

    
//    public String saveUploadedFile(UploadedFile uploadedFile) {
//        System.out.println("uploadedFile :" + uploadedFile);
//        if (uploadedFile != null) {
//            String uploadFolderPath = "/home/ksouraj/Uploads/";
//            Path folderPath = Paths.get(uploadFolderPath);
//            InputStream in = null;
//            try {
//                Files.createDirectories(folderPath);
//
//                String str = uploadedFile.getFileName();
//                in = uploadedFile.getInputstream();
//                try (OutputStream out = new FileOutputStream(new File(uploadFolderPath + str))) {
//                    int read = 0;
//                    byte[] bytes = new byte[1024];
//                    while ((read = in.read(bytes)) != -1) {
//                        out.write(bytes, 0, read);
//                    }
//                    in.close();
//                    out.flush();
//                }
//            } catch (IOException ex) {
//                FacesMessage message = new FacesMessage("Error", "Error while uploading the file.");
//                FacesContext.getCurrentInstance().addMessage(null, message);
//            } finally {
//                try {
//                    in.close();
//                } catch (IOException ex) {
//                    FacesMessage message = new FacesMessage("Error", "Error while uploading the file.");
//                    FacesContext.getCurrentInstance().addMessage(null, message);
//                }
//            }
//            return "/Uploads/" + uploadedFile.getFileName();
//
//        }
//        return null;
//    }

    public boolean isFileTypeAllowed(UploadedFile uploadedFile) {
        String fileName = uploadedFile.getFileName();
        if (fileName != null) {
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
            return "jpg".equalsIgnoreCase(extension) || "pdf".equalsIgnoreCase(extension);
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

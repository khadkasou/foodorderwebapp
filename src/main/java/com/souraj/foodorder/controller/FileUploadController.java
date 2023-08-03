//package com.souraj.foodorder.controller;
//
//import com.souraj.foodorder.model.File;
//import com.souraj.foodorder.repository.FileRepository;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
//import org.primefaces.model.UploadedFile;
//
//import javax.annotation.PostConstruct;
//import javax.faces.application.FacesMessage;
//import javax.faces.context.FacesContext;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.UUID;
//import org.apache.commons.io.FilenameUtils;
//
//@Named(value = "fileUploadController")
//@ViewScoped
//public class FileUploadController implements Serializable {
//
//    private List<File> uploadedFiles;
//    private StreamedContent streamedContent;
//
//    @Inject
//    private FileRepository fileRepository;
//
//    @PostConstruct
//    public void init() {
//        uploadedFiles = new ArrayList<>();
//    }
//
//    public List<File> getUploadedFiles() {
//        return uploadedFiles;
//    }
//
//    public StreamedContent getStreamedContent() {
//        return streamedContent;
//    }
//
//    public void saveUploadedFile(UploadedFile uploadedFile) {
//        if (uploadedFile != null) {
//            try {
//
//                Iterator<File> fileIterator = uploadedFiles.iterator();
//                while (fileIterator.hasNext()) {
//                    File existingFile = fileIterator.next();
//                    if (existingFile.getFileName().equals(uploadedFile.getFileName())) {
//                        deleteFile(existingFile);
//                        fileIterator.remove();
//                        break;
//                    }
//                }
//                String uploadFolderPath = "/home/ksouraj/Uploads";
//                Path folderPath = Paths.get(uploadFolderPath);
//                Files.createDirectories(folderPath);
//
//                String uniqueFileName = UUID.randomUUID().toString()
//                        + "." + FilenameUtils.getExtension(uploadedFile.getFileName());
//                Path filePath = folderPath.resolve(uniqueFileName);
//
//                try (InputStream inputStream = uploadedFile.getInputstream(); OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
//                    byte[] buffer = new byte[1024];
//                    int bytesRead;
//                    while ((bytesRead = inputStream.read(buffer)) != -1) {
//                        outputStream.write(buffer, 0, bytesRead);
//                    }
//                }
//
//                File file = new File();
//                file.setFileName(uploadedFile.getFileName());
//                file.setFileSize((int) uploadedFile.getSize());
//                file.setFilePath("/Uploads/" + uniqueFileName);
//
//                uploadedFiles.add(file);
//                FacesMessage message = new FacesMessage("Successful", uploadedFile.getFileName() + " is uploaded.");
//                FacesContext.getCurrentInstance().addMessage(null, message);
//            } catch (IOException e) {
//                FacesMessage message = new FacesMessage("Error", "Error while uploading the file.");
//                FacesContext.getCurrentInstance().addMessage(null, message);
//            }
//        }
//    }
//
//    public void deleteFile(File file) {
//        try {
//            Path fileToDelete = Paths.get(file.getFilePath());
//            Files.deleteIfExists(fileToDelete);
//            uploadedFiles.remove(file);
//        } catch (IOException e) {
//            FacesMessage message = new FacesMessage("Error", "Error while deleting the file.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//    }
//
//    public void viewFile(File file) {
//        String filePath = "/home/ksouraj/Uploads/" + file.getFilePath();
//        try {
//            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
//            String contentType = Files.probeContentType(Paths.get(filePath));
//            streamedContent = new DefaultStreamedContent(inputStream, contentType, file.getFileName());
//        } catch (IOException e) {
//            FacesMessage message = new FacesMessage("Error", "Error while viewing the file.");
//            FacesContext.getCurrentInstance().addMessage(null, message);
//        }
//    }
//
//    public void uploadFiles() {
//        for (File file : uploadedFiles) {
//            fileRepository.save(file);
//        }
//        uploadedFiles.clear();
//    }
//}
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

//    public void handleFileUpload(FileUploadEvent event) {
//    UploadedFile uploadedFile = event.getFile();
//    File file = new File();
//    file.setFileName(uploadedFile.getFileName());
//    file.setFileSize((int) uploadedFile.getSize());
//
//    String uploadFolderPath = "/home/ksouraj/Uploads";
//
//    try {
//        Path folderPath = Paths.get(uploadFolderPath);
//        Files.createDirectories(folderPath);
//
//        String uniqueFileName = uploadedFile.getFileName();
//
//        Path filePath = folderPath.resolve(uniqueFileName);
//        try (InputStream inputStream = uploadedFile.getInputstream();
//             OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//        }
//
//        file.setFilePath(uploadFolderPath + "/" + uniqueFileName);
//
//        uploadedFiles.add(file);
//    } catch (IOException e) {
//    }
//}
//    public void handleFileUpload(FileUploadEvent event) {
//        FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
//        FacesContext.getCurrentInstance().addMessage(null, msg);
//    }
    public String saveUploadedFile(UploadedFile uploadedFile) {
        System.out.println("uploadedFile :"+uploadedFile);
        if (uploadedFile != null) {
            try {
                String uploadFolderPath = "/home/ksouraj/Uploads";
                System.out.println("uploadFolderPath2323 :" + uploadFolderPath);
                Path folderPath = Paths.get(uploadFolderPath);
                System.out.println("folderPath :"+folderPath);
                Files.createDirectories(folderPath);
System.out.println("CREated"+uploadedFile);
//                String uniqueFileName = UUID.randomUUID().toString() + 
//                        "." + FilenameUtils.getExtension(uploadedFile.getFileName());
//                System.out.println("uploadedFile.getFileName() :" + uploadedFile);
//                Path filePath = folderPath.resolve(uploadedFile.getFileName());
File outF= new File(uploadFolderPath);
                System.out.println("uploadedFile.getInputstream()"+uploadedFile.getInputstream());
                try (InputStream inputStream = uploadedFile.getInputstream(); OutputStream outputStream = new FileOutputStream(outF)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }

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

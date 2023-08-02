package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.File;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

@Named(value = "fileUploadController")
@ViewScoped
public class FileUploadController implements Serializable {

    private List<File> uploadedFiles;
    private StreamedContent streamedContent;

    @PostConstruct
    public void init() {
        uploadedFiles = new ArrayList<>();
    }

    public List<File> getUploadedFiles() {
        return uploadedFiles;
    }

    public StreamedContent getStreamedContent() {
        return streamedContent;
    }

    public void handleFileUpload(FileUploadEvent event) {
        UploadedFile uploadedFile = event.getFile();
        File file = new File();
        file.setFileName(uploadedFile.getFileName());
        file.setFileSize((int) uploadedFile.getSize());

        String uploadFolderPath = "/home/ksouraj/Uploads"; 

        try {
            Path folderPath = Paths.get(uploadFolderPath);
            Files.createDirectories(folderPath);

            String uniqueFileName = uploadedFile.getFileName(); 

            Path filePath = folderPath.resolve(uniqueFileName);
            try (InputStream inputStream = uploadedFile.getInputstream(); OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            file.setFilePath(uploadFolderPath + "/" + uniqueFileName);
            uploadedFiles.add(file); 

        } catch (IOException e) {
        }
    }

    public void deleteFileByPath(String filePath) {
        try {
            if (filePath != null && !filePath.isEmpty()) {
                Path fileToDelete = Paths.get(filePath);
                Files.delete(fileToDelete);
            }
        } catch (IOException e) {
        }
    }

    public void viewFile(File file) {
        String filePath = file.getFilePath();

        try {
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            String contentType = Files.probeContentType(Paths.get(filePath));
            streamedContent = new DefaultStreamedContent(inputStream, contentType);
        } catch (IOException e) {
        }
    }

    public void clearUploadedFiles() {
        uploadedFiles.clear();
    }
}

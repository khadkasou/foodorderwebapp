package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.File;
import com.souraj.foodorder.model.FileRecords;
import com.souraj.foodorder.repository.FileRecordsRepository;
import com.souraj.foodorder.repository.FileRepository;
import org.apache.commons.io.FilenameUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named(value = "fileUploadController")
@ViewScoped
public class FileUploadController implements Serializable {

    private List<FileRecords> fileRecordsList;
    private List<File> uploadedFiles;
    private StreamedContent streamedContent;
    private UploadedFile uploadedFile;
    private String uploadedFilePath; // New variable to store the uploaded file path

    @Inject
    private FileRepository fileRepository;

    @Inject
    private FileRecordsRepository fileRecordsRepository;

    @PostConstruct
    public void init() {
        fileRecordsList = new ArrayList<>();
        uploadedFiles = new ArrayList<>();
    }

    public List<FileRecords> getFileRecordsList() {
        return fileRecordsList;
    }

    public List<File> getUploadedFiles() {
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

    public String getUploadedFilePath() {
        return uploadedFilePath;
    }

    public void setUploadedFilePath(String uploadedFilePath) {
        this.uploadedFilePath = uploadedFilePath;
    }

    public void saveUploadedFile() {
        if (uploadedFile != null) {
            try {
                String uploadFolderPath = "/home/ksouraj/Uploads"; 
                Path folderPath = Paths.get(uploadFolderPath);
                Files.createDirectories(folderPath);

                String uniqueFileName = uploadedFile.getFileName();
                Path filePath = folderPath.resolve(uniqueFileName);

                // Replace existing file if a file with the same name already exists
                int counter = 1;
                while (Files.exists(filePath)) {
                    String baseName = FilenameUtils.getBaseName(uniqueFileName);
                    String extension = FilenameUtils.getExtension(uniqueFileName);
                    uniqueFileName = baseName + "-" + counter + "." + extension;
                    filePath = folderPath.resolve(uniqueFileName);
                    counter++;
                }

                try (InputStream inputStream = uploadedFile.getInputstream();
                     OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                }

                File file = new File();
                file.setFileName(uniqueFileName);
                file.setFileSize((int) uploadedFile.getSize());
                file.setFilePath(uniqueFileName);

                uploadedFiles.add(file);
                uploadedFilePath = uniqueFileName; // Store the file path in the uploadedFilePath variable
                FacesMessage message = new FacesMessage("Successful", uploadedFile.getFileName() + " is uploaded.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            } catch (IOException e) {
                FacesMessage message = new FacesMessage("Error", "Error while uploading the file.");
                FacesContext.getCurrentInstance().addMessage(null, message);
            }
        }
    }

    public void viewFile(File file) {
        String filePath = "/home/ksouraj/uploads/" + file.getFilePath();

        try {
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            String contentType = Files.probeContentType(Paths.get(filePath));
            streamedContent = new DefaultStreamedContent(inputStream, contentType);
        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Error", "Error while viewing the file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void deleteFile(String filePath) {
        try {
            Path fileToDelete = Paths.get("/home/ksouraj/uploads/" + filePath);
            Files.deleteIfExists(fileToDelete);
        } catch (IOException e) {
            FacesMessage message = new FacesMessage("Error", "Error while deleting the file.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }

    public void uploadFiles() {
        for (File file : uploadedFiles) {
            fileRepository.save(file);
            FileRecords fileRecord = new FileRecords();
            fileRecord.setFile(file);
            fileRecord.setLocation(uploadedFilePath); 
            fileRecord.setFileName(file.getFileName());
            fileRecordsRepository.save(fileRecord);
            fileRecordsList.add(fileRecord);
        }
        uploadedFiles.clear();
    }
}

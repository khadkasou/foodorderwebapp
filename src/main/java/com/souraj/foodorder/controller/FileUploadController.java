package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.File;
import com.souraj.foodorder.model.FileRecords;
import com.souraj.foodorder.repository.FileRecordsRepository;
import com.souraj.foodorder.repository.FileRepository;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.io.Serializable;

@Named(value = "fileUploadController")
@ViewScoped
public class FileUploadController implements Serializable {

    private List<FileRecords> fileRecordsList;
    private List<File> uploadedFiles;
    private StreamedContent streamedContent;

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

    public void handleFileUpload(FileUploadEvent event) {
        uploadedFiles.clear(); // Clear previous uploaded files if any

        UploadedFile uploadedFile = event.getFile();
        File file = new File();
        file.setFileName(uploadedFile.getFileName());
        file.setFileSize((int) uploadedFile.getSize());
        String savedFilePath = saveFile(uploadedFile);
        file.setFilePath(savedFilePath);
        uploadedFiles.add(file);
    }
    public String saveFile(UploadedFile uploadedFile) {
        String uploadFolderPath = "/home/ksouraj/Uploads"; // Update with your desired folder path
        try {
            Path folderPath = Paths.get(uploadFolderPath);
            Files.createDirectories(folderPath);

            String uniqueFileName = UUID.randomUUID().toString() + "_" + uploadedFile.getFileName();

            Path filePath = folderPath.resolve(uniqueFileName);
            try (InputStream inputStream = uploadedFile.getInputstream();
                 OutputStream outputStream = Files.newOutputStream(filePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            return uploadFolderPath + "/" + uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void viewFile(FileRecords fileRecord) {
        String filePath = fileRecord.getFile().getFilePath();

        try {
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            String contentType = Files.probeContentType(Paths.get(filePath));
            streamedContent = new DefaultStreamedContent(inputStream, contentType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void uploadFiles() {
        for (File file : uploadedFiles) {
            fileRepository.save(file);
            FileRecords fileRecord = new FileRecords();
            fileRecord.setFile(file);
            fileRecord.setLocation(file.getFilePath());
            fileRecord.setFileName(file.getFileName());
            fileRecordsRepository.save(fileRecord);
            fileRecordsList.add(fileRecord);
        }
        uploadedFiles.clear();
    }
}

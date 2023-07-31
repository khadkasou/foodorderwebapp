package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.File;
import com.souraj.foodorder.model.FileRecords;
import com.souraj.foodorder.repository.FileRepository;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * FileUploadController to handle file upload and manage file records.
 */
@Named(value = "fileUploadController")
@ViewScoped
public class FileUploadController implements Serializable {

    private List<FileRecords> fileRecordsList;
    private List<File> uploadedFiles;

    @Inject
    private FileRepository fileRepository;

    @PostConstruct
    public void init() {
        fileRecordsList = new ArrayList<>();
        uploadedFiles = new ArrayList<>();
    }

    public void handleFileUpload(org.primefaces.event.FileUploadEvent event) {
        org.primefaces.model.UploadedFile uploadedFile = event.getFile();
        File file = new File();
        file.setFileName(uploadedFile.getFileName());
        file.setFileSize((int) uploadedFile.getSize());
        file.setFilePath("ksouraj/home/fileUpload"); 
        uploadedFiles.add(file);
    }

    public void uploadFiles() {
        for (File file : uploadedFiles) {
            fileRepository.save(file); 
            FileRecords fileRecord = new FileRecords();
            fileRecord.setFile(file);
            fileRecord.setLocation(file.getFilePath());
            fileRecord.setFileName(file.getFileName());
            fileRecordsList.add(fileRecord);
        }
        uploadedFiles.clear();
    }

    public List<FileRecords> getFileRecordsList() {
        return fileRecordsList;
    }
}

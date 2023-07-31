/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.File;
import com.souraj.foodorder.model.FileRecords;
import com.souraj.foodorder.repository.FileRecordsRepository;
import com.souraj.foodorder.repository.FileRepository;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;




/**
 *
 * @author ksouraj
 */
@Named(value = "fileUploadController")
@ViewScoped
public class FileUploadController implements Serializable {
 
     @Inject
    private FileRepository fileRepository;

     @Inject FileRecordsRepository fileRecordsRepository;
    private FileRecords selectedFile;
    private byte[] uploadedFile;

    @PostConstruct
    public void init() {
        selectedFile = new FileRecords();
    }

    public void handleFileUpload(FileUploadEvent event) {
        uploadedFile = event.getFile().getContents();
        selectedFile.setFileName(event.getFile().getFileName());
        selectedFile.setFileSize(uploadedFile.length);
        String fileLocation = "ksouraj/home/Document/" + selectedFile.getFileName();
        selectedFile.setLocation(fileLocation);
    }

    public FileRecords getSelectedFile() {
        return selectedFile;
    }

    public void setSelectedFile(FileRecords selectedFile) {
        this.selectedFile = selectedFile;
    }

    public byte[] getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(byte[] uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public void saveFileRecord() {
        File file = new File();
        file.setFileName(selectedFile.getFileName());
        file.setFileSize(selectedFile.getFileSize());
        file.setFilePath(selectedFile.getLocation());

        fileRepository.save(file);

        selectedFile.setFile(file);
        fileRecordsRepository.save(selectedFile);
    }
}


    



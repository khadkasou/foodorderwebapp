/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.File;
import com.souraj.foodorder.model.FileRecords;
import com.souraj.foodorder.repository.FileRecordsRepository;
import com.souraj.foodorder.repository.FileRepository;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;




/**
 *
 * @author ksouraj
 */
@Named(value = "fileUploadController")
@ViewScoped
public class FileUploadController implements Serializable {
 
    private UploadedFile uploadedFile;
    private String fileName;

    @Inject
    private FileRepository fileRepository;
    
    @Inject
    private FileRecordsRepository recordsRepository;

    // Getters and Setters (Omitted for brevity)

    public void handleFileUpload() throws Exception {
        try {
            String basePath = "ksouraj/home/Documents/"; 
            String location = basePath + fileName;

           
            uploadedFile.write(basePath + fileName);

            
             File file = new File();
               file.setFileName(fileName);
               file.setFileSize((int) uploadedFile.getSize());
               file.setFilePath(basePath);
               fileRepository.save(file);
               
            
             FileRecords fileRecord = new FileRecords();
             fileRecord.setFile(file);
             fileRecord.setFileName(fileName);
             fileRecord.setLocation(location);
             recordsRepository.save(fileRecord);

        } catch (IOException e) {
            e.printStackTrace();
            
        }
    }
}

    



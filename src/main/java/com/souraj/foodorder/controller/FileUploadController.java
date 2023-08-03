//package com.souraj.foodorder.controller;
//
//import com.souraj.foodorder.model.File;
//import com.souraj.foodorder.model.FileRecords;
//import com.souraj.foodorder.model.UploadFile;
//import com.souraj.foodorder.repository.FileRecordsRepository;
//import com.souraj.foodorder.repository.FileRepository;
//import org.primefaces.event.FileUploadEvent;
//import org.primefaces.model.DefaultStreamedContent;
//import org.primefaces.model.StreamedContent;
//import org.primefaces.model.UploadedFile;
//
//import javax.annotation.PostConstruct;
//import javax.faces.view.ViewScoped;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//import java.io.Serializable;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//@Named(value = "fileUploadController")
//@ViewScoped
//public class FileUploadController implements Serializable {
//
//    private List<FileRecords> fileRecordsList;
//    private List<File> uploadedFiles;
//    private StreamedContent streamedContent;
//    private UploadFile uploadFile;
//    private List<UploadFile> uploadFiles;
//
//    public List<UploadFile> getUploadFiles() {
//        return uploadFiles;
//    }
//
//    public void setUploadFiles(List<UploadFile> uploadFiles) {
//        this.uploadFiles = uploadFiles;
//    }
//
//    @Inject
//    private FileRepository fileRepository;
//
//    @Inject
//    private FileRecordsRepository fileRecordsRepository;
//
//    @PostConstruct
//    public void init() {
//        fileRecordsList = new ArrayList<>();
//        uploadedFiles = new ArrayList<>();
//        uploadFiles = new ArrayList<>();
//    }
//
//    public List<FileRecords> getFileRecordsList() {
//        return fileRecordsList;
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
//    public UploadFile getUploadFile() {
//        return uploadFile;
//    }
//
//    public void setUploadFile(UploadFile uploadFile) {
//        this.uploadFile = uploadFile;
//    }
//    
////    public void handleFileUpload(FileUploadEvent event) {
////    UploadedFile uploadedFile = event.getFile();
////    //File file = new File();
////    this.uploadFile.setFileName(uploadedFile.getFileName());
////    this.uploadFile.setFileSize((int) uploadedFile.getSize());
////    InputStream inputStream;
////        try {
////            inputStream = uploadedFile.getInputstream();
////            this.uploadFile.setInputStream(inputStream);
////        } catch (IOException ex) {
////            Logger.getLogger(FileUploadController.class.getName()).log(Level.SEVERE, null, ex);
////        }
////    
////    }
////public void upload(UploadFile file){
////    String uploadFolderPath = "/home/ksouraj/Uploads";
////
////    try {
////        Path folderPath = Paths.get(uploadFolderPath);
////        Files.createDirectories(folderPath);
////
////        String uniqueFileName = file.getFileName();
////
////        Path filePath = folderPath.resolve(uniqueFileName);
////        try (InputStream inputStream = file.getInputStream();
////             OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
////            byte[] buffer = new byte[1024];
////            int bytesRead;
////            while ((bytesRead = inputStream.read(buffer)) != -1) {
////                outputStream.write(buffer, 0, bytesRead);
////            }
////        }
////
////        file.setFilePath(uploadFolderPath + "/" + uniqueFileName);
////
////        uploadFiles.add(file);
////    } catch (IOException e) {
////    }
////}
//
//   public void handleFileUpload(FileUploadEvent event) {
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
//
//
//    public void viewFile(FileRecords fileRecord) {
//        String filePath = fileRecord.getFile().getFilePath();
//
//        try {
//            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
//            String contentType = Files.probeContentType(Paths.get(filePath));
//            streamedContent = new DefaultStreamedContent(inputStream, contentType);
//        } catch (IOException e) {
//        }
//    }
//
//    public void uploadFiles() {
//        for (File file : uploadedFiles) {
//            fileRepository.save(file);
//            FileRecords fileRecord = new FileRecords();
//            fileRecord.setFile(file);
//            fileRecord.setLocation(file.getFilePath());
//            fileRecord.setFileName(file.getFileName());
//            fileRecordsRepository.save(fileRecord);
//            fileRecordsList.add(fileRecord);
//        }
//        uploadedFiles.clear();
//    }
//}

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
        try (InputStream inputStream = uploadedFile.getInputstream();
             OutputStream outputStream = new FileOutputStream(filePath.toFile())) {
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


    public void viewFile(FileRecords fileRecord) {
        String filePath = fileRecord.getFile().getFilePath();

        try {
            InputStream inputStream = Files.newInputStream(Paths.get(filePath));
            String contentType = Files.probeContentType(Paths.get(filePath));
            streamedContent = new DefaultStreamedContent(inputStream, contentType);
        } catch (IOException e) {
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
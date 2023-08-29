/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.FileUpload;
import com.souraj.foodorder.repository.FileUploadRepository;
import java.io.FileInputStream;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class FileDownloadController implements Serializable {

    private StreamedContent file;

    @Inject
    private FileUploadRepository fileUploadRepository;

    public void setFile(StreamedContent file) {
        this.file = file;
    }

//    public StreamedContent downloadFile(Long id) {
//        try {
//            FileUpload fileUpload = fileUploadRepository.findById(id);
//
//            if (fileUpload == null) {
//                System.out.println("File not found for id: " + id);
//                return null;
//            }
//
//            String fileLocation = fileUpload.getLocation();
//
//            if (fileLocation != null) {
//
//                InputStream stream = new FileInputStream(fileUpload.getLocation());
//
//                if (stream != null) {
//                    String contentType = "application/pdf";
//
//                    String fileName = "fileName.pdf";
//                    return new DefaultStreamedContent(stream, contentType, fileName);
//                } else {
//                    System.out.println("Stream is null : " + fileLocation);
//                    return null;
//                }
//            } else {
//                System.out.println("File location is null: " + id);
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    
    public StreamedContent downloadFile(Long id) {
    try {
        FileUpload fileUpload = fileUploadRepository.findById(id);

        if (fileUpload == null) {
            System.out.println("File not found for id: " + id);
            return null;
        }

        String fileLocation = fileUpload.getLocation();

        if (fileLocation != null) {
            InputStream stream = new FileInputStream(fileUpload.getLocation());

            if (stream != null) {
                String contentType;
                String fileName = fileUpload.getfName(); 

                // Determine content type based on file extension
                String extension = getFileExtension(fileName);
                switch (extension.toLowerCase()) {
                    case "pdf":
                        contentType = "application/pdf";
                        break;
                    case "jpg":
                    case "jpeg":
                        contentType = "image/jpeg";
                        break;
                    case "png":
                        contentType = "image/png";
                        break;
                    default:
                        contentType = "application/octet-stream"; 
                }

                return new DefaultStreamedContent(stream, contentType, fileName);
            } else {
                System.out.println("Stream is null : " + fileLocation);
                return null;
            }
        } else {
            System.out.println("File location is null: " + id);
            return null;
        }
    } catch (Exception e) {
        e.printStackTrace();
        return null;
    }
}


private String getFileExtension(String fileName) {
    int dotIndex = fileName.lastIndexOf(".");
    if (dotIndex > 0) {
        return fileName.substring(dotIndex + 1);
    }
    return "";
}


}

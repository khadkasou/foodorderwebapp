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

    @Inject
    private FileUploadRepository fileUploadRepository;
    
     private final String basePath ="/home/synergy/Uploads/";

    public StreamedContent downloadFile(Long id) {
        try {
            FileUpload fileUpload = fileUploadRepository.findById(id);

            if (fileUpload == null) {
                return null;
            }

            String fileLocation = basePath+fileUpload.getLocation();

            if (fileLocation != null) {
                InputStream stream = new FileInputStream(fileLocation);

                if (stream != null) {
                    String contentType;
                    String fileName = fileUpload.getfName();

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
                    return null;
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getFileExtension(String fileName) {
        int f = fileName.lastIndexOf(".");
        if (f > 0) {
            return fileName.substring(f + 1);
        }
        return "";
    }

}

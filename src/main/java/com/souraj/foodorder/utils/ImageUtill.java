/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.utils;

import com.souraj.foodorder.repository.FileUploadRepository;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author synergy
 */
@Model
public class ImageUtill implements Serializable {

    @Inject
    private FileUploadRepository fileUploadRepository;
    
//    @Inject
//    private FileUploadController fileUploadController;


    public StreamedContent loadFileData() throws FileNotFoundException, IOException {
        FacesContext context = FacesContext.getCurrentInstance();
        String id = context.getExternalContext().getRequestParameterMap().get("docId");

        if (id != null) {
            String fileLocation = fileUploadRepository.findById(Long.valueOf(id)).getLocation();
            File file = new File(fileLocation);
            byte[] newByte = Files.readAllBytes(file.toPath());

//            String contentType = "application/pdf";
//            
//            if (fileUploadController.isImage()) {
//                contentType = "image/png";
//            }

            return new DefaultStreamedContent(new ByteArrayInputStream(newByte), "*/*");
        }
        return new DefaultStreamedContent();
    }
}

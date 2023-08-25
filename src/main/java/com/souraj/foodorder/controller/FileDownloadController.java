/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.repository.FileUploadRepository;
import java.io.InputStream;
import org.primefaces.model.StreamedContent;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.model.DefaultStreamedContent;

@Named(value = "fileDownloadController")
@SessionScoped
public class FileDownloadController implements Serializable {

    @Inject
    private FileUploadRepository fileUploadRepository;

    private StreamedContent file;

    public StreamedContent getFile() {
        return file;
    }

    public void setFile(StreamedContent file) {
        this.file = file;
    }
    
    public void downloadFile() {
        FacesContext context = FacesContext.getCurrentInstance();
        String id = context.getExternalContext().getRequestParameterMap().get("docId");
        String fileLocation = fileUploadRepository.findById(Long.valueOf(id)).getLocation();

        InputStream stream = ((ServletContext) FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getContext())
                .getResourceAsStream(fileLocation);

        file = new DefaultStreamedContent(stream);
    }
}

//    public FileDownloadController() {
//        FacesContext context = FacesContext.getCurrentInstance();
//        String id = context.getExternalContext().getRequestParameterMap().get("docId");
//        FileUpload fileUpload = fileUploadRepository.findById(Long.valueOf(id));
//
//        InputStream stream = ((ServletContext) FacesContext
//                .getCurrentInstance()
//                .getExternalContext()
//                .getContext())
//                .getResourceAsStream(fileUpload.getLocation());
//
//        String contentType = determineContentType(fileUpload.getfName());
//        file = new DefaultStreamedContent(stream, contentType, fileUpload.getfName());
//
//    }
//
//    private String determineContentType(String fileName) {
//        String extension = fileName.substring(fileName.lastIndexOf('.') + 1).toLowerCase();
//        switch (extension) {
//            case "jpg":
//            case "jpeg":
//            case "png":
//                return "image/" + extension;
//            case "pdf":
//                return "application/pdf";
//            default:
//                return "application/octet-stream";
//        }
//    }


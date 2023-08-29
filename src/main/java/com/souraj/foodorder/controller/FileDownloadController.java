/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.FileUpload;
import com.souraj.foodorder.repository.FileUploadRepository;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import java.io.InputStream;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.imageio.ImageIO;
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

    public StreamedContent fileDownload(Long id) throws IOException {

        String fileName = fileUploadRepository.findById(Long.valueOf(id)).getLocation();
        String contentType = "application/pdf";
        //  InputStream inputStream = this.getClass().getResourceAsStream("/path/to/" + fileName);

        BufferedImage thumb = ImageIO.read(new File(fileName));
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        ImageIO.write(thumb,
                contentType, os);

        try (InputStream is = new ByteArrayInputStream(os.toByteArray())) {
            file = new DefaultStreamedContent(is, contentType);
        }
        return file;
    }

//    public StreamedContent downloadFile(Long id) {
//
//        System.out.println("ID issssssss: " + id);
//
//        try {
//            String fileLocation = fileUploadRepository.findById(id).getLocation();
//
//            System.out.println("File Location: " + fileLocation);
//
//            if (fileLocation != null) {
//                InputStream stream = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext())
//                        .getResourceAsStream(fileLocation);
//
//                if (stream != null) {
//                    String contentType = "application/pdf";
//                    return new DefaultStreamedContent(stream, contentType, "fileName.pdf");
//                } else {
//                    System.out.println("Stream is null for file location: " + fileLocation);
//                    return null;
//                }
//            } else {
//                System.out.println("File location is null for ID: " + id);
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    public StreamedContent downloadFile(Long id) {
//
//        try {
//            FileUpload fileUpload = fileUploadRepository.findById(id);
//
//            if (fileUpload == null) {
//                System.out.println("id not found: " + id);
//                return null;
//            }
//
//            String fileLocation = fileUpload.getLocation();
//
//            if (fileLocation != null) {
//                InputStream stream = FacesContext.getCurrentInstance().getExternalContext()
//                        .getResourceAsStream(fileLocation);
//
//                if (stream != null) {
//                    String contentType = "application/pdf";
//                    String fileName = "fileName.pdf";
//                    return new DefaultStreamedContent(stream, contentType, fileName);
//                } else {
//                    System.out.println("Stream is null: " + fileLocation);
//                    return null;
//                }
//            } else {
//                System.out.println("File location: " + id);
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//    
    public StreamedContent downloadFile(Long id) {
        try {
            FileUpload fileUpload = fileUploadRepository.findById(id);

            if (fileUpload == null) {
                System.out.println("File not found for id: " + id);
                return null;
            }

            String fileLocation = fileUpload.getLocation();

            if (fileLocation != null) {
//                InputStream stream = FacesContext.getCurrentInstance().getExternalContext()
//                        .getResourceAsStream(fileLocation);
                InputStream stream = new FileInputStream(fileUpload.getLocation());

                if (stream != null) {
                    String contentType = "application/pdf";
                    String fileName = "fileName.pdf";
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

//    public StreamedContent downloadFile(Long id) {
//
//        try {
//            FileUpload fileUpload = fileUploadRepository.findById(id);
//
//            if (fileUpload == null) {
//                System.out.println("File not found for ID: " + id);
//                return null;
//            }
//
//            String fileName = fileUpload.getfName();
//            String contentType = "application/pdf";
//
//            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileUpload.getLocation());
//
//            if (stream != null) {
//                return new DefaultStreamedContent(stream, contentType, fileName);
//            } else {
//                System.out.println("Stream is null for file location: " + fileUpload.getLocation());
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
}

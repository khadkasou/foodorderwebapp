/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.repository.FileUploadRepository;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
import javax.servlet.ServletContext;

@Named
@ViewScoped
public class FileDownloadController implements Serializable {

    private StreamedContent file;

    @Inject
    private FileUploadRepository fileUploadRepository;

    public void setFile(StreamedContent file) {
        this.file = file;
    }

    public void fileDownload(Long id) throws IOException {

        String fileName = fileUploadRepository.findById(Long.valueOf(id)).getLocation();
        String contentType = "application/pdf";
        //  InputStream inputStream = this.getClass().getResourceAsStream("/path/to/" + fileName);

        BufferedImage thumb = ImageIO.read(new File(fileName));
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        ImageIO.write(thumb,
                contentType, os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        file = new DefaultStreamedContent(is, contentType);
    }

    public StreamedContent downloadFile(Long id) {

        String filess = fileUploadRepository.findById(Long.valueOf(id)).getLocation();
        InputStream stream = ((ServletContext)FacesContext
                .getCurrentInstance().getExternalContext().getContext())
                .getResourceAsStream(filess);
        String contentType = "application/pdf";
        return new DefaultStreamedContent(stream, contentType, "fileName.pdf");

    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.utils.Utils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.servlet.http.Part;

/**
 *
 * @author ksouraj
 */
@ManagedBean
@ViewScoped
public class FileUploadController implements Serializable {

    private Part file1;
    private Part file2;
    private String message;

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

    public Part getFile2() {
        return file2;
    }

    public void setFile2(Part file2) {
        this.file2 = file2;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String uploadFile() throws IOException {
        boolean file1Success = false;

        if (file1 != null && file1.getSize() > 0) {
            String fileName = Utils.getFileNameFromPart(file1);
            
             // destination where the file will be uploaded
             File savedFile = new File("/home/ksouraj/uploaded", fileName);

            // System.out.println("savedFile.toPath(): " + savedFile.toPath());
            try (InputStream input = file1.getInputStream()) {
                Files.copy(input, savedFile.toPath());
            } catch (IOException e) {
            }

            file1Success = true;
        }

        boolean file2Success = false;

        if (file2 != null && file2.getSize() > 0) {
            String fileName = Utils.getFileNameFromPart(file2);
            File savedFile = new File("/home/ksouraj/uploaded", fileName);

            System.out.println(savedFile.getAbsolutePath());

            try (InputStream input = file2.getInputStream()) {
                Files.copy(input, savedFile.toPath());
            } catch (IOException e) {
            }

            file2Success = true;
        }

        if (file1Success || file2Success) {
            setMessage("File successfully uploaded");
        } else {

            setMessage("Error, select atleast one file!");
        }
        return null;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.FileUpload;
import com.souraj.foodorder.repository.FileUploadRepository;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author ksouraj
 */
@ManagedBean(name = "fileUploadController")
@SessionScoped
public class FlileUploadController {

    private FileUpload fileUpload;
    private List<FileUpload> uploadedFileList;
    private UploadedFile uploadedFile;
    byte[] selectedFileContent;
    byte[] file;

    @Inject
    private FileUploadRepository fileUploadRepository;

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }

    public List<FileUpload> getUploadedFileList() {
        return uploadedFileList;
    }

    public void setUploadedFileList(List<FileUpload> uploadedFileList) {
        this.uploadedFileList = uploadedFileList;
    }

 

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public byte[] getSelectedFileContent() {
        return selectedFileContent;
    }

    public void setSelectedFileContent(byte[] selectedFileContent) {
        this.selectedFileContent = selectedFileContent;
    }

    @PostConstruct
    public void init() {
        this.fileUpload = new FileUpload();
    }

    public void beforeCreate() {
        this.fileUpload = new FileUpload();
    }

    public void beforeUpdate(FileUpload fu) {
        this.fileUpload = fileUploadRepository.findById(fu.getId());

    }

}

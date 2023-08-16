/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ksouraj
 */
@Entity
@Table(name = "file_upload")
public class FileUpload extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "file_name_id")
    private FileName fileNameId;
    private String Remarks;
    @Column(name = "file_name")
    private String fName;
    private String createdBy;
    private String location;
    

    public FileUpload() {
    }

    public FileName getFileNameId() {
        return fileNameId;
    }

    public void setFileNameId(FileName fileNameId) {
        this.fileNameId = fileNameId;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    


    @Override
    public String getTableName() {
        return "file_upload";
    }

}

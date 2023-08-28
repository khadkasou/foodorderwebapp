/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.FileUpload;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ksouraj
 */
@Stateless
public class FileUploadRepository extends GenericAbstractClasss<FileUpload> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public FileUploadRepository() {
        super(FileUpload.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FileUpload findById(FileUpload fileId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

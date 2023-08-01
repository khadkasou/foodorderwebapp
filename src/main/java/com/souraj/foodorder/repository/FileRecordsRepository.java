/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.FileRecords;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ksouraj
 */
@Stateless
public class FileRecordsRepository extends GenericAbstractClasss<FileRecords>{

    @PersistenceContext(name = "EPE")
    private EntityManager em;
    
    public FileRecordsRepository() {
        super(FileRecords.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}

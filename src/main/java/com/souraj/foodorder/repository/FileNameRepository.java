/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.FileName;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ksouraj
 */
@Stateless
public class FileNameRepository extends GenericAbstractClasss<FileName> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public FileNameRepository() {
        super(FileName.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

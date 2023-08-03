package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.File;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FileRepository extends GenericAbstractClasss<File> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public FileRepository() {
        super(File.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    
}

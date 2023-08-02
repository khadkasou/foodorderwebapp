package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.File;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public boolean existsByFilePath(String filePath) {
        Query query = em.createQuery("SELECT COUNT(f) FROM File f WHERE f.filePath = :filePath");
        query.setParameter("filePath", filePath);
        Long count = (Long) query.getSingleResult();
        return count > 0;
    }

    public File findByFilePath(String filePath) {
        Query query = em.createQuery("SELECT f FROM File f WHERE f.filePath = :filePath", File.class);
        query.setParameter("filePath", filePath);
        return (File) query.getSingleResult();
    }

    public void delete(File file) {
        if (file != null && file.getId() != null) {
            file = em.find(File.class, file.getId());
            em.remove(file);
        }
    }
}

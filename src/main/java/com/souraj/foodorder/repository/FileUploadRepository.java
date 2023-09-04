/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.FileUpload;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

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

    public List<FileUpload> loadDataLazy(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<FileUpload> cq = cb.createQuery(FileUpload.class);
        Root<FileUpload> root = cq.from(FileUpload.class);

        List<Predicate> predicates = new ArrayList<>();

      
        if (filters != null && !filters.isEmpty()) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String filterField = entry.getKey();
                Object filterValue = entry.getValue();
                if (filterValue != null) {
                    predicates.add(cb.equal(root.get(filterField), filterValue));
                }
            }
        }

        if (sortField != null && !sortField.isEmpty()) {
            if (sortOrder == SortOrder.ASCENDING) {
                cq.orderBy(cb.asc(root.get(sortField)));
            } else if (sortOrder == SortOrder.DESCENDING) {
                cq.orderBy(cb.desc(root.get(sortField)));
            }
        }

        cq.select(root);
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        return getEntityManager()
                .createQuery(cq)
                .setFirstResult(first)
                .setMaxResults(pageSize)
                .getResultList();
    }

    public int countDataLazy(Map<String, Object> filters) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<FileUpload> root = cq.from(FileUpload.class);

        List<Predicate> predicates = new ArrayList<>();
        if (filters != null && !filters.isEmpty()) {
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                String filterField = entry.getKey();
                Object filterValue = entry.getValue();
                if (filterValue != null) {
                    predicates.add(cb.equal(root.get(filterField), filterValue));
                }
            }
        }

        cq.select(cb.count(root));
        if (!predicates.isEmpty()) {
            cq.where(predicates.toArray(new Predicate[0]));
        }

        Long count = getEntityManager().createQuery(cq).getSingleResult();
        return count != null ? count.intValue() : 0;
    }

}

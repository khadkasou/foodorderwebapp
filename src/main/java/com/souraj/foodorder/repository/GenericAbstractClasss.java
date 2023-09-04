/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.IAbstractClass;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author ksouraj
 * @param <T>
 */
public abstract class GenericAbstractClasss<T extends IAbstractClass> implements GenericRepo<T> {

    protected abstract EntityManager getEntityManager();
    protected CriteriaQuery<T> criteriaQuery;
    protected CriteriaBuilder criteriaBuilder;
    protected Root<T> root;
    private Class<T> entityClass;
    protected List<Predicate> predicates;

    public GenericAbstractClasss(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public Class<T> getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public CriteriaQuery<T> getCriteriaQuery() {
        return criteriaQuery;
    }

    public void setCriteriaQuery(CriteriaQuery<T> criteriaQuery) {
        this.criteriaQuery = criteriaQuery;
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public void setCriteriaBuilder(CriteriaBuilder criteriaBuilder) {
        this.criteriaBuilder = criteriaBuilder;
    }

    public Root<T> getRoot() {
        return root;
    }

    public void setRoot(Root<T> root) {
        this.root = root;
    }

    @PostConstruct
    protected void _startQuery() {
        this.criteriaBuilder = getEntityManager().getCriteriaBuilder();
        this.criteriaQuery = this.criteriaBuilder.createQuery(getEntityClass());
        root = this.criteriaQuery.from(getEntityClass());
        predicates = new ArrayList<>();
    }

    public GenericAbstractClasss<T> startQuery() {
        this._startQuery();
        return this;
    }

    @Override
    public void save(T object) {
        getEntityManager().persist(object);
        getEntityManager().flush();
    }

    /**
     *
     * @param object
     */
    @Override
    public void update(T object) {
        getEntityManager().merge(object);
        getEntityManager().flush();

    }

    @Override
    public List<T> findAll() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(entityClass));
        Query q = getEntityManager().createQuery(cq);
        List<T> results = q.getResultList();
        return results;
    }

    @Override
    public T findById(Long id) {
        return getEntityManager().find(entityClass, id);
    }

    @Override
    public void delete(Long id) {
        getEntityManager().remove(findById(id));
        getEntityManager().flush();
    }
    
     public GenericAbstractClasss<T> addCriteria(Predicate p) {
        this.predicates.add(p);
        return this;
    }

    public T getSingleResult() {
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        return getEntityManager().createQuery(criteriaQuery).getSingleResult();
    }

    public List<T> getResultList() {
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public List<T> getResultList(int first, int pageSize) {
        criteriaQuery.where(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])));
        List<T> data = getEntityManager().createQuery(criteriaQuery)
                .setFirstResult(first).setMaxResults(pageSize)
                .getResultList();
        return data;
    }

}

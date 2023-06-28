/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.IAbstractClass;
import javax.persistence.EntityManager;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ksouraj
 * @param <T>
 */
public abstract class AbstractClass<T extends IAbstractClass> implements GenericRepo<T> {

    protected abstract EntityManager getEntityManager();
    protected CriteriaQuery<T> criteriaQuery;
    protected CriteriaBuilder criteriaBuilder;
    private Class<T> entityClass;
    protected Root<T> root;

    public AbstractClass(Class<T> entityClass) {
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
        
    }

    @Override
    public List<T> findAll() {
        criteriaBuilder = getEntityManager().getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(entityClass);
        root = criteriaQuery.from(entityClass);
        criteriaQuery = criteriaQuery.select(root);

        return getEntityManager().createQuery(criteriaQuery).getResultList();
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

}

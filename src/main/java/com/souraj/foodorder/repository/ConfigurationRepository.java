/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.Configuration;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ksouraj
 */
@Stateless
public class ConfigurationRepository extends GenericAbstractClasss<Configuration> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public ConfigurationRepository() {
        super(Configuration.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

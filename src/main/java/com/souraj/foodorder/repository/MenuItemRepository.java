/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.MenuItem;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ksouraj
 */
@Stateless
public class MenuItemRepository extends AbstractClass<MenuItem> {

    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public MenuItemRepository() {
        super(MenuItem.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

}

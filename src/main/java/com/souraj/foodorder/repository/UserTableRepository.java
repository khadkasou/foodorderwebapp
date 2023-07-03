/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import com.souraj.foodorder.model.UserTable;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ksouraj
 */
@Stateless
public class UserTableRepository extends GenericAbstractClasss<UserTable>{
    
    @PersistenceContext(name = "EPE")
    private EntityManager em;

    public UserTableRepository() {
        super(UserTable.class);
    }

    @Override
    protected EntityManager getEntityManager() {
      return em;
    }
    
}

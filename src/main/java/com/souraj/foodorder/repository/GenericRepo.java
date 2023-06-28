/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import java.sql.ResultSet;
import java.util.List;
/**
 *
 * @author ksouraj
 * @param <T>
 */
public interface GenericRepo<T> {

    
  public void save(T object);
    
   public void delete(Long id);
   
   public List<T> findAll();
   
   public T findById(Long id);
   
   public void update(T object);
       
    
}

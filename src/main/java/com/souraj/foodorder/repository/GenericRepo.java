/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import java.util.List;

/**
 *
 * @author ksouraj
 * @param <T>
 */
public interface GenericRepo<T> {
    
   public T save(T Object);
    
   public void deleteById(Long id);
   
   public List<T> findAll();
   
   public T findById(Long id);
   
   public T updateById(T Object, Long id );
   
    
}

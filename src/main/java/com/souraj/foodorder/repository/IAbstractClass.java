/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;

import java.time.LocalDate;

/**
 *
 * @author ksouraj
 */
public interface IAbstractClass {
    
   Long getId();
   void setId(Long id);
   
    LocalDate getCreatedAt();
   void setCreatedAt(LocalDate creadtedAt);
   
   LocalDate getUpdatedAt();
   void setUpdatedAt(LocalDate updatedAt);
   
   String getTableName();
   
   
    
}

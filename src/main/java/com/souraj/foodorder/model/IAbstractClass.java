/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import java.util.Date;

/**
 *
 * @author ksouraj
 */
public interface IAbstractClass {
    
   Long getId();
   void setId(Long id);
   
    Date getCreatedAt();
   void setCreatedAt(Date creadtedAt);
   
   Date getUpdatedAt();
   void setUpdatedAt(Date updatedAt);
   
   String getTableName();
   
   
    
}

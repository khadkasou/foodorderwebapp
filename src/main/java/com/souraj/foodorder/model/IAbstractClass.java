/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import java.time.LocalDateTime;

/**
 *
 * @author ksouraj
 */
public interface IAbstractClass {

    Long getId();

    void setId(Long id);

    LocalDateTime getCreatedAt();

    void setCreatedAt(LocalDateTime creadtedAt);

    LocalDateTime getUpdatedAt();

    void setUpdatedAt(LocalDateTime updatedAt);

    String getTableName();

}

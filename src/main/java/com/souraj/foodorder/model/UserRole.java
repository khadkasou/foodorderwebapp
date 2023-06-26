/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import com.souraj.foodorder.repository.AbstractEntity;
import com.souraj.foodorder.repository.IAbstractClass;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author ksouraj
 */
@Entity
@Table(name = "user_role")
public class UserRole extends AbstractEntity implements IAbstractClass, Serializable {

    @Column(name = "name", unique = true)
    private String name;

    public UserRole() {

    }

    public UserRole(String name) {
        this.name = name;
    }
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getTableName() {
        return "user_role";
    }

}

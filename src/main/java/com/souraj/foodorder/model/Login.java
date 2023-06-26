/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.souraj.foodorder.model;

import com.souraj.foodorder.repository.AbstractEntity;
import com.souraj.foodorder.repository.IAbstractClass;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Entity;
import javax.persistence.Table;



/**
 *
 * @author ksouraj
 */
@ManagedBean(name = "login")
@SessionScoped
@Entity
@Table(name = "login")
public class Login extends AbstractEntity implements IAbstractClass, Serializable{

   
    private String usernmae;
    private String password;

    public String getUsernmae() {
        return usernmae;
    }

    public void setUsernmae(String usernmae) {
        this.usernmae = usernmae;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
//    public boolean checkUser(){
//       
//    }
    
    
    public Login() {
        
    }

    @Override
    public String getTableName() {
        return "login";
    }
    
}

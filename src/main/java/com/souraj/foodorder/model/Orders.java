/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author ksouraj
 */
@Entity
@Table(name = "orders")
public class Orders extends AbstractEntity{

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "user")
    private UserTable user;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date orderDate;
    private String status;
    private String remarks;
    private String descriptions;

    public Orders() {
    }

    
  
    
    public UserTable getUser() {
        return user;
    }

    public void setUser(UserTable user) {
        this.user = user;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }
    
    
    
    @Override
    public String getTableName() {
        return "orders";
    }
    
    
    
}

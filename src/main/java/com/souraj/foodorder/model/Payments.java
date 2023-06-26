/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import com.souraj.foodorder.repository.AbstractEntity;
import com.souraj.foodorder.repository.IAbstractClass;
import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ksouraj
 */
@Entity
@Table(name = "payments")
public class Payments extends AbstractEntity implements IAbstractClass, Serializable{

    @ManyToOne
    private Orders order;
    @ManyToOne
    private UserTable user;
    private LocalDate paymentDate;
    private Double vat;
    private Double discount;
    private Double serviceCharge;
    private Double total;

    public Payments() {
    }

    

    
    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public UserTable getUser() {
        return user;
    }

    public void setUser(UserTable user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return paymentDate;
    }

    public void setDate(LocalDate date) {
        this.paymentDate = date;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(Double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    
    @Override
    public String getTableName() {
            return "payments";
    }
    
    
    
}

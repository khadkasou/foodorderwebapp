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
@Table(name = "payments")
public class Payments extends AbstractEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order")
    private Orders order;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user")
    private UserTable user;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date paymentDate;
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

    public Date getDate() {
        return paymentDate;
    }

    public void setDate(Date date) {
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

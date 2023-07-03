/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Orders;
import com.souraj.foodorder.repository.OrdersRepository;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ksouraj
 */
@Named(value = "ordersController")
@ViewScoped
public class OrdersController implements Serializable {

    private Orders orders;
    private List<Orders> ordersList;

    @Inject
    private OrdersRepository ordersRepository;

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public void init() {
        this.orders = new Orders();
        dataLoader();

    }

    public void beforeCreate() {
        this.orders = new Orders();

    }

    public void beforeUpdate(Orders o) {
        this.orders = ordersRepository.findById(o.getId());
    }

    public void addOrder() {
        ordersRepository.save(orders);
        dataLoader();
    }

    public void update() {
        ordersRepository.update(this.orders);
        dataLoader();
    }

    public void delete(Long id) {
        ordersRepository.delete(id);
        dataLoader();
    }

    public void dataLoader() {
        this.ordersList = new ArrayList<>();
        this.ordersList = ordersRepository.findAll();
    }

}

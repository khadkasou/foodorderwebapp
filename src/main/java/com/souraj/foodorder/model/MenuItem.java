/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ksouraj
 */
@Entity
@Table(name = "menu_item")
public class MenuItem extends AbstractEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menu")
    private Menu menu;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_items")
    private FoodItems foodItems;
    
    private Double price;

    public MenuItem() {
        
    }

    
   
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public FoodItems getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(FoodItems foodItems) {
        this.foodItems = foodItems;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    

    
    
    
}

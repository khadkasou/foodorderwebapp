/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.model;

import com.souraj.foodorder.repository.AbstractEntity;
import com.souraj.foodorder.repository.IAbstractClass;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ksouraj
 */
@Entity
@Table(name = "menu_item")
public class MenuItem extends AbstractEntity implements IAbstractClass, Serializable{

    @ManyToOne
    private Menu menu;
    @ManyToOne
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
    
    
    
            
            
    @Override
    public String getTableName() {
        return "menu_item";
               
    }
    
    
    
}

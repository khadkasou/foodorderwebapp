/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.FoodItems;
import com.souraj.foodorder.repository.FoodItemsRepository;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author ksouraj
 */
@Named(value = "foodItemsController")
@ViewScoped
public class FoodItemsController implements Serializable {

    private FoodItems foodItems;
    private List<FoodItems> foodItemsList;
    
    @Inject
  private FoodItemsRepository foodItemsRepository;

    public FoodItems getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(FoodItems foodItems) {
        this.foodItems = foodItems;
    }

    public List<FoodItems> getFoodItemsList() {
        return foodItemsList;
    }

    public void setFoodItemsList(List<FoodItems> foodItemsList) {
        this.foodItemsList = foodItemsList;
    }

    @PostConstruct
    public void init() {
        this.foodItems = new FoodItems();
        loadDate();

    }

    public void beforeCreate() {
        this.foodItems = new FoodItems();
    }

    public void beforeUpdate(FoodItems fi) {
        this.foodItems = foodItemsRepository.findById(fi.getId());
    }

    public void addFoodItems() {
        foodItemsRepository.save(foodItems);
    
        loadDate();
    }

    public void update() {
        foodItemsRepository.update(this.foodItems);
        loadDate();
    }

    public void delete(Long id) {
        foodItemsRepository.delete(id);
        loadDate();
    }

    public void loadDate() {
        this.foodItemsList = new ArrayList<>();
        this.foodItemsList = foodItemsRepository.findAll();
    }

}

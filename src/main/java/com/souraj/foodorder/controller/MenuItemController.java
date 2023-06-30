/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.MenuItem;
import com.souraj.foodorder.repository.MenuItemRepository;
import java.io.Serializable;
import java.util.ArrayList;
import javax.inject.Named;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
/**
 *
 * @author ksouraj
 */
@Named(value = "menuItemController")
@ViewScoped
public class MenuItemController implements Serializable{
    
    private MenuItem menuItem;
    private List<MenuItem> menuItemList;
    
    
    @Inject
    private MenuItemRepository menuItemRepository;
    
    
    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public void setMenuItemList(List<MenuItem> menuItemList) {
        this.menuItemList = menuItemList;
    }
    
    public void beforeCreate() {
        this.menuItem = new MenuItem();
    }

    public void beforeUpdate(MenuItem mi) {
        this.menuItem = menuItemRepository.findById(mi.getId());
    }
    
    public void init(){
        this.menuItem= new MenuItem();
        dataLoader();
        
    }
    public void addMenuItem(){
        menuItemRepository.save(menuItem);
        dataLoader();
    }
    
    public void update(){
        menuItemRepository.update(this.menuItem);
    }
    
    public void delete(Long id){
        menuItemRepository.delete(id);
    }
    
    
    public void dataLoader(){
        this.menuItemList = new ArrayList<>();
        this.menuItemList= menuItemRepository.findAll();
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Menu;
import com.souraj.foodorder.repository.MenuRepository;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 *
 * @author ksouraj
 */
@Named(value = "menuController")
@ViewScoped
public class MenuController implements Serializable {

    private Menu menu;
    private List<Menu> menuList;

    @Inject
    private MenuRepository menuRepository;

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    @PostConstruct
    public void init() {
        this.menu = new Menu();
        loadData();

    }

    public void beforeCreate() {
        this.menu = new Menu();
    }

    public void beforeUpdate(Menu m) {
        this.menu = menuRepository.findById(m.getId());
    }

    public void createMenu() {
        menuRepository.save(menu);
        loadData();
    }

    public void update() {
        menuRepository.update(this.menu);
        loadData();
    }

    public void delete(Long id) {
        menuRepository.delete(id);
        loadData();
    }

    public void loadData() {
        this.menuList = menuRepository.findAll();

    }

}

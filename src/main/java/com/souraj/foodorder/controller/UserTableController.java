/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.UserTable;
import com.souraj.foodorder.repository.UserTableRepository;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author ksouraj
 */
@Named(value = "userController")
@ViewScoped
public class UserTableController implements Serializable {

    private UserTable userTable;
    private List<UserTable> userList;

    private UserTableRepository userTableRepository;

    public UserTable getUser() {
        return userTable;
    }

    public void setUser(UserTable userTable) {
        this.userTable = userTable;
    }

    public List<UserTable> getUserList() {
        return userList;
    }

    public void setUserList(List<UserTable> userList) {
        this.userList = userList;
    }

    @PostConstruct
    public void init() {
        this.userTable = new UserTable();
        dataLoader();

    }

    public void beforeCreate() {
        this.userTable = new UserTable();
    }

    public void beforeUpdate(UserTable ut) {
        this.userTable = userTableRepository.findById(ut.getId());
    }
    
    public void addUser(){
        userTableRepository.save(userTable);
        dataLoader();
    }
    
    public void update(){
        userTableRepository.update(this.userTable);
    }
    
    public void delete(Long id){
        userTableRepository.delete(id);
    }

    public void dataLoader() {
        this.userList = new ArrayList<>();
        this.userList = userTableRepository.findAll();

    }

}

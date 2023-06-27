/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.repository.CategoryRepo;
import java.io.Serializable;
import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author ksouraj
 */
@Named
@ViewScoped
public class CategoryController implements Serializable {

    private List<Category> categories;
    private Category category;

    private CategoryRepo categoryRepo;

    public List<Category> getCategories() {

        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @PostConstruct
    public void init() {

        categories = new ArrayList<>();
        category = new Category();

    }

    public void saveCategory() {
        categoryRepo.save(category);
        this.categories = categoryRepo.findAll();
    }

    public void deleteById(Long id) {
        categoryRepo.deleteById(id);
        this.categories = categoryRepo.findAll();

    }

    public void findById(Long id) {
        categoryRepo.findById(id);
        this.categories = categoryRepo.findAll();

    }

    public void findAll() {
        categoryRepo.findAll();
    }

    public void updateById(Long id) {
        categoryRepo.updateById(category, id);
    }

}

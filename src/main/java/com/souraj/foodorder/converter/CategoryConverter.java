/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.converter;

import com.souraj.foodorder.repository.GenericAbstractClasss;
import com.souraj.foodorder.repository.CategoryRepo;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;


import com.souraj.foodorder.model.Category;

/**
 *
 * @author ksouraj
 */
@FacesConverter(value = "categoryConverter", forClass = Category.class)
public class CategoryConverter extends GenericConverter {

    @Inject
    private CategoryRepo repo;

    @Override
    protected GenericAbstractClasss getAbstractRepo() {
        return repo;
    }

}

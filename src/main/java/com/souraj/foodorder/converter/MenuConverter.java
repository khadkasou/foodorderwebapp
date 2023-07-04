/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.converter;
import com.souraj.foodorder.model.Menu;
import com.souraj.foodorder.repository.GenericAbstractClasss;
import com.souraj.foodorder.repository.MenuRepository;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author ksouraj
 */
@FacesConverter(value = "menuConverter", forClass = Menu.class)
public class MenuConverter extends GenericConverter {

    @Inject
    private MenuRepository menuRepo;

    @Override
    protected GenericAbstractClasss getAbstractRepo() {
        return menuRepo;
    }

}

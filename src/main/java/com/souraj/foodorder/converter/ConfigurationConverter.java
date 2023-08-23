/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.converter;

import com.souraj.foodorder.model.Configuration;
import com.souraj.foodorder.repository.ConfigurationRepository;
import com.souraj.foodorder.repository.GenericAbstractClasss;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author ksouraj
 */
@FacesConverter(value = "configurationConverter", forClass = Configuration.class)

public class ConfigurationConverter extends GenericConverter{
    @Inject
    private ConfigurationRepository configurationRepo;

    @Override
    protected GenericAbstractClasss getAbstractRepo() {
            return configurationRepo;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.converter;

import com.souraj.foodorder.model.FileName;
import com.souraj.foodorder.repository.FileNameRepository;
import com.souraj.foodorder.repository.GenericAbstractClasss;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author ksouraj
 */
@FacesConverter(value = "fileNameConverter", forClass = FileName.class)
public class FileNameConverter extends GenericConverter {
    
    
    @Inject
    private FileNameRepository fileRepo;

    @Override
    protected GenericAbstractClasss getAbstractRepo() {
        return fileRepo;
    }
    
}

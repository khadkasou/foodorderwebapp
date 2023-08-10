/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.converter;

import com.souraj.foodorder.model.FileUpload;
import com.souraj.foodorder.repository.FileUploadRepository;
import com.souraj.foodorder.repository.GenericAbstractClasss;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author ksouraj
 */
@FacesConverter(value = "fileUploadConverter", forClass = FileUpload.class)
public class FileUploadConverter extends GenericConverter{

    @Inject
    private FileUploadRepository fileUploadRepository;
    
    @Override
    protected GenericAbstractClasss getAbstractRepo() {
        return fileUploadRepository;
    }
   

}

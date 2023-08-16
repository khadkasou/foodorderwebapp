package com.souraj.foodorder.model;

import com.souraj.foodorder.model.FileName;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-16T10:38:20")
@StaticMetamodel(FileUpload.class)
public class FileUpload_ extends AbstractEntity_ {

    public static volatile SingularAttribute<FileUpload, String> fName;
    public static volatile SingularAttribute<FileUpload, String> Remarks;
    public static volatile SingularAttribute<FileUpload, String> createdBy;
    public static volatile SingularAttribute<FileUpload, String> location;
    public static volatile SingularAttribute<FileUpload, FileName> fileNameId;

}
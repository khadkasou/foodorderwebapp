package com.souraj.foodorder.model;

import com.souraj.foodorder.model.Configuration;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-25T11:22:24")
@StaticMetamodel(FileUpload.class)
public class FileUpload_ extends AbstractEntity_ {

    public static volatile SingularAttribute<FileUpload, String> fName;
    public static volatile SingularAttribute<FileUpload, Configuration> configuration;
    public static volatile SingularAttribute<FileUpload, String> createdBy;
    public static volatile SingularAttribute<FileUpload, String> location;
    public static volatile SingularAttribute<FileUpload, String> remarks;

}
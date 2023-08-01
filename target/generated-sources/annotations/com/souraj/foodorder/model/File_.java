package com.souraj.foodorder.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-01T15:01:05")
@StaticMetamodel(File.class)
public class File_ extends AbstractEntity_ {

    public static volatile SingularAttribute<File, String> fileName;
    public static volatile SingularAttribute<File, Integer> fileSize;
    public static volatile SingularAttribute<File, String> filePath;

}
package com.souraj.foodorder.model;

import com.souraj.foodorder.model.File;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-08T17:17:04")
@StaticMetamodel(FileRecords.class)
public class FileRecords_ extends AbstractEntity_ {

    public static volatile SingularAttribute<FileRecords, String> fileName;
    public static volatile SingularAttribute<FileRecords, File> file;
    public static volatile SingularAttribute<FileRecords, String> location;

}
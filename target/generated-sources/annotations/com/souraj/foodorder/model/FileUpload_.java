package com.souraj.foodorder.model;

import com.souraj.foodorder.model.FileName;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-10T17:40:28")
@StaticMetamodel(FileUpload.class)
public class FileUpload_ extends AbstractEntity_ {

    public static volatile SingularAttribute<FileUpload, String> fileName;
    public static volatile SingularAttribute<FileUpload, Date> createdDate;
    public static volatile SingularAttribute<FileUpload, String> Remarks;
    public static volatile SingularAttribute<FileUpload, String> createdBy;
    public static volatile SingularAttribute<FileUpload, FileName> fileNameId;

}
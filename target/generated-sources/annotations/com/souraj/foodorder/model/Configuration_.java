package com.souraj.foodorder.model;

import java.util.List;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-16T10:38:20")
@StaticMetamodel(Configuration.class)
public class Configuration_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Configuration, Integer> fsize;
    public static volatile SingularAttribute<Configuration, String> name;
    public static volatile SingularAttribute<Configuration, String> location;
    public static volatile SingularAttribute<Configuration, List> allowedType;

}
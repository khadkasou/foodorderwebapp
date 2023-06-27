package com.souraj.foodorder.model;

import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-27T14:48:32")
@StaticMetamodel(Menu.class)
public class Menu_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Menu, LocalDate> fromDate;
    public static volatile SingularAttribute<Menu, LocalDate> toDate;
    public static volatile SingularAttribute<Menu, String> name;

}
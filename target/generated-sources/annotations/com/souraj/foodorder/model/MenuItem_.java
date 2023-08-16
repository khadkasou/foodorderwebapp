package com.souraj.foodorder.model;

import com.souraj.foodorder.model.FoodItems;
import com.souraj.foodorder.model.Menu;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-16T10:38:20")
@StaticMetamodel(MenuItem.class)
public class MenuItem_ extends AbstractEntity_ {

    public static volatile SingularAttribute<MenuItem, Double> price;
    public static volatile SingularAttribute<MenuItem, FoodItems> foodItems;
    public static volatile SingularAttribute<MenuItem, Menu> menu;

}
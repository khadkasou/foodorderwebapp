package com.souraj.foodorder.model;

import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.model.Menu;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-09T13:59:56")
@StaticMetamodel(FoodItems.class)
public class FoodItems_ extends AbstractEntity_ {

    public static volatile SingularAttribute<FoodItems, Double> price;
    public static volatile SingularAttribute<FoodItems, String> name;
    public static volatile SingularAttribute<FoodItems, String> description;
    public static volatile SingularAttribute<FoodItems, Category> category;
    public static volatile SingularAttribute<FoodItems, Menu> menu;

}
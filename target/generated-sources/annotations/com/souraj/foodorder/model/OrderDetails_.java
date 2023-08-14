package com.souraj.foodorder.model;

import com.souraj.foodorder.model.MenuItem;
import com.souraj.foodorder.model.Orders;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-14T15:32:33")
@StaticMetamodel(OrderDetails.class)
public class OrderDetails_ extends AbstractEntity_ {

    public static volatile SingularAttribute<OrderDetails, Double> quantity;
    public static volatile SingularAttribute<OrderDetails, Double> rate;
    public static volatile SingularAttribute<OrderDetails, MenuItem> menuItem;
    public static volatile SingularAttribute<OrderDetails, String> status;
    public static volatile SingularAttribute<OrderDetails, Orders> order;

}
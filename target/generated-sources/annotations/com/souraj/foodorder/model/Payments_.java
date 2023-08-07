package com.souraj.foodorder.model;

import com.souraj.foodorder.model.Orders;
import com.souraj.foodorder.model.UserTable;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-07T17:17:29")
@StaticMetamodel(Payments.class)
public class Payments_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Payments, Double> serviceCharge;
    public static volatile SingularAttribute<Payments, Double> total;
    public static volatile SingularAttribute<Payments, Double> vat;
    public static volatile SingularAttribute<Payments, Double> discount;
    public static volatile SingularAttribute<Payments, Date> paymentDate;
    public static volatile SingularAttribute<Payments, UserTable> user;
    public static volatile SingularAttribute<Payments, Orders> order;

}
package com.souraj.foodorder.model;

import com.souraj.foodorder.model.UserTable;
import com.souraj.foodorder.repository.AbstractEntity_;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-06-26T14:23:33")
@StaticMetamodel(Orders.class)
public class Orders_ extends AbstractEntity_ {

    public static volatile SingularAttribute<Orders, UserTable> user;
    public static volatile SingularAttribute<Orders, LocalDate> orderDate;
    public static volatile SingularAttribute<Orders, String> descriptions;
    public static volatile SingularAttribute<Orders, String> remarks;
    public static volatile SingularAttribute<Orders, String> status;

}
package com.souraj.foodorder.model;

import com.souraj.foodorder.model.UserRole;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-16T10:38:20")
@StaticMetamodel(UserTable.class)
public class UserTable_ extends AbstractEntity_ {

    public static volatile SingularAttribute<UserTable, String> firstName;
    public static volatile SingularAttribute<UserTable, String> lastName;
    public static volatile SingularAttribute<UserTable, String> password;
    public static volatile SingularAttribute<UserTable, String> address;
    public static volatile SingularAttribute<UserTable, UserRole> roles;
    public static volatile SingularAttribute<UserTable, String> email;
    public static volatile SingularAttribute<UserTable, String> username;

}
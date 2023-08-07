package com.souraj.foodorder.model;

import java.time.LocalDateTime;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-08-07T13:59:34")
@StaticMetamodel(AbstractEntity.class)
public abstract class AbstractEntity_ { 

    public static volatile SingularAttribute<AbstractEntity, LocalDateTime> createdAt;
    public static volatile SingularAttribute<AbstractEntity, Long> id;
    public static volatile SingularAttribute<AbstractEntity, LocalDateTime> updatedAt;

}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.repository;
import com.souraj.foodorder.model.IAbstractClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author ksouraj
 * @param <T>
 */
public abstract class AbstractClass<T extends IAbstractClass> implements GenericRepo<T>{


    private final List<T> database ;
    
    
    
    public AbstractClass(){
        database = new ArrayList<>();
     
    }
      
    
    @Override
    public T save(T Object) {
        database.add(Object);
        return Object;
    }
    
         
    @Override
    public void deleteById(Long id){
        T t = findById(id);
        database.remove(t);
    }

        
    @Override
    public List<T> findAll() {
        return database;
    }

    @Override
    public T findById(Long id) {
         
    for (T categ : database) {
        if (Objects.equals(categ.getId(), id)) {
            return categ;
        }
    }
    return null;
      
    }
    
    
   @Override
   public T updateById(T obj, Long id) {
//    for (int i = 0; i < database.size(); i++) {
//        T t = database.get(i);
//        if (t instanceof Identifiable) {
//            Identifiable identifiable = (Identifiable) t;
//            if (identifiable.getId() == id) {
//                database.set(i, obj);
//                return obj;
//            }
//        }
//    }
    return null; 
}



}

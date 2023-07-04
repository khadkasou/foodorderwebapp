///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.souraj.foodorder.converter;
//
//import com.souraj.foodorder.model.Category;
//import com.souraj.foodorder.repository.CategoryRepo;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//import javax.faces.convert.FacesConverter;
//import javax.inject.Inject;
//
///**
// *
// * @author ksouraj
// */
//@FacesConverter(value = "catConverter", forClass = Category.class)
//public class CatConverter implements Converter {
//
//    @Inject
//    CategoryRepo categoryRepository;
//
//    @Override
//    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
//        System.out.println("heloooo" + fc + "," + uic + "," + value);
//        if (value == null || value.isEmpty() || value.length() == 0 || value.equals("")) {
//            return null;
//        }
//        return categoryRepository.findById(Long.valueOf(value));
//    }
//
//    @Override
//    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
//        if (o == null || o.equals("")) {
//            return "";
//        }
//        return ((Category) o).getId().toString();
//    }
//
//}
//
//
////    @Override
////    public Object getAsObject(FacesContext context, UIComponent component, String value) {
////        if (value == null || value.isEmpty()) {
////            return null;
////        }
////        Category selectedCategory = null;
////        try {
////            Long cateId = Long.valueOf(value);
////            selectedCategory= categoryRepository.findById(cateId);
////            return selectedCategory;
////        } catch (NumberFormatException e) {
////        }
////        return selectedCategory;
////    }
////    
////     @Override
////    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
////        if (o instanceof Category) {
////            Category category= (Category) o;
////            return String.valueOf(category.getId());
////        }
////        return null;
////        
////    }
////}
//
//

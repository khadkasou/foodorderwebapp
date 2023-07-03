///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package converter;
//
//import com.souraj.foodorder.model.AbstractEntity;
//import com.souraj.foodorder.repository.GenericAbstractClasss;
//import javax.faces.component.UIComponent;
//import javax.faces.context.FacesContext;
//import javax.faces.convert.Converter;
//
///**
// *
// * @author ksouraj
// */
//public abstract class GenericConverter implements Converter {
//
//    protected abstract GenericAbstractClasss getAbstractRepo();
//
//    @Override
//    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
//        if (value == null || value.isEmpty() || value.length() == 0 || value.equals("")) {
//            return null;
//        }
//        return getAbstractRepo().findById(Long.valueOf(value));
//    }
//
//    @Override
//    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
//        if (o == null || o.equals("")) {
//            return "";
//        }
//        if (o instanceof AbstractEntity) {
//            AbstractEntity ab = (AbstractEntity) o;
//            return ab.getId().toString();
//        } else {
//            throw new IllegalArgumentException("conversion fail...");
//
//        }
//    }
//}

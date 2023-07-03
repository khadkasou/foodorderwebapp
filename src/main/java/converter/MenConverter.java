/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package converter;

import com.souraj.foodorder.model.Menu;
import com.souraj.foodorder.repository.MenuRepository;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

/**
 *
 * @author ksouraj
 */
@FacesConverter(value = "menConverter", forClass = Menu.class)
public class MenConverter implements Converter {

    @Inject
    MenuRepository menuRepository;

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if (value == null || value.isEmpty() || value.length() == 0 || value.equals("")) {
            return null;
        }
        return menuRepository.findById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o == null || o.equals("")) {
            return "";
        }
        return ((Menu) o).getId().toString();
    }
}

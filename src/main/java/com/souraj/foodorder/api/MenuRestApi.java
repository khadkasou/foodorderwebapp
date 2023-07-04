/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.souraj.foodorder.model.Menu;
import com.souraj.foodorder.repository.MenuRepository;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ksouraj
 */
@Path("/menu")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MenuRestApi {
    
    
    @Inject
    private MenuRepository menuRepository;
    
    
    @POST
    public Response addMenu(Menu menu) throws JsonProcessingException{
        
        menuRepository.save(menu);
        
        ObjectMapper mapper = new ObjectMapper();
        
        String str = mapper.writeValueAsString(menu);
        
        return RestResponse.responseBuilder("true","201",
                "menu added successfully" , str);
        
    }
    
    
     @GET
    public Response getAllMenu() throws JsonProcessingException {
        
        List<Menu> menuList= menuRepository.findAll();

        
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(menuList);

        return RestResponse.responseBuilder("true", "200", "List of categories", str);
    }
    
}

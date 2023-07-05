/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.souraj.foodorder.dto.MenuDto;
import com.souraj.foodorder.model.Menu;
import com.souraj.foodorder.repository.MenuRepository;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
    public Response addMenu(Menu menu) throws JsonProcessingException {

        menuRepository.save(menu);

          //   return Response.ok().entity(menu).build();
        ObjectMapper mapper = new ObjectMapper();

        String str = mapper.writeValueAsString(menu);

        return RestResponse.responseBuilder("true", "201",
                "Menu added successfully", str);

    }

    @GET
    public Response getAllMenu() throws JsonProcessingException {

        List<Menu> menuList = menuRepository.findAll();

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(menuList);

        return RestResponse.responseBuilder("true", "200", "List of menus", str);
    }

    @GET
    @Path("/{id}")
    public Response getMenuById(@PathParam("id") Long id) throws JsonProcessingException {

        Menu me = menuRepository.findById(id);

        if (me == null) {
            return RestResponse.responseBuilder("false", "404", "Menu with id " + id + " not found", null);
        }
        ObjectMapper mapper = new ObjectMapper();
       String str = mapper.writeValueAsString(me);
         //  return Response.ok().entity(me).build();
        return RestResponse.responseBuilder("true", "200", "Menu with id " + id + " found", str);
    }

    @PUT
    @Path("/{id}")
    public Response updateMenu(@PathParam("id") Long id, MenuDto menuDto) throws JsonProcessingException {

        Menu menu = menuRepository.findById(id);
        if (menu == null) {
            return RestResponse.responseBuilder("false", "404", "Menu with id " + id + " not found", null);
        }

        menu.setName(menuDto.getName());
        menu.setFromDate(menuDto.getFromDate());
        menu.setToDate(menuDto.getToDate());

        menuRepository.update(menu);

        //  return Response.ok().entity(menu).build();
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(menu);

        return RestResponse.responseBuilder("true", "200", "Menu updated successfully", str);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMenu(@PathParam("id") Long id) throws JsonProcessingException {

        Menu menu = menuRepository.findById(id);
        if (menu == null) {
            return RestResponse.responseBuilder("false", "404", "Menu with id " + id + " not found", null);
        }
        menuRepository.delete(id);
        return RestResponse.responseBuilder("true", "200", "Menu with id " + id + " deleted successfully", null);
    }

}

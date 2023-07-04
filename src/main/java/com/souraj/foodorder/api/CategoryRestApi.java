/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.souraj.foodorder.model.Category;
import com.souraj.foodorder.repository.CategoryRepo;
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
@Path("/category")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CategoryRestApi {

    @Inject
    private CategoryRepo categoryRepo;

    @POST
    public Response addCategory(Category category) throws JsonProcessingException {

          categoryRepo.save(category);

        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(category);

        return RestResponse.responseBuilder("true", "201", "category created successfully", str);

    
    }

    @GET
    public Response getAllCategories() throws JsonProcessingException {
        
        List<Category> categoryList= categoryRepo.findAll();

        
        ObjectMapper mapper = new ObjectMapper();
        String str = mapper.writeValueAsString(categoryList);

        return RestResponse.responseBuilder("true", "200", "List of categories", str);
    }

}

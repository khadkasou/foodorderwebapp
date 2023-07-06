/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.souraj.foodorder.model.FoodItems;
import com.souraj.foodorder.repository.FoodItemsRepository;
import javax.inject.Inject;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ksouraj
 */
@Path("/foodItems")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class FoodItemsApi {
    
    @Inject
    private FoodItemsRepository foodItemsRepo;
    
    
    
    @GET
    public Response saveFoodItems(FoodItems foodItems) throws JsonProcessingException{
       foodItemsRepo.save(foodItems);
       
        ObjectMapper mapper = new ObjectMapper();
        
        String str = mapper.writeValueAsString(foodItems);
        
        return RestResponse.responseBuilder(true, "201", "FoodItems created successfully", 
                (JsonObject) mapper.readTree(str));
        
    }
    
}

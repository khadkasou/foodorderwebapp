/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.restClient;

import com.souraj.foodorder.api.RestResponse;
import com.souraj.foodorder.model.Category;
import java.io.Serializable;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.core.GenericType;

@RequestScoped
public class CategoryRestClient implements Serializable {

    private static final String BASE_URL = "http://10.120.3.176:8080/foodorderwebapp-1.0-SNAPSHOT/api";
    private static final String RESOURCE_URL = "/category";
    private final Client client;

    private List<Category> categoryList;

    public CategoryRestClient() {
        client = ClientBuilder.newClient();
    }

    public Response createCategory(Category category) {
        Response response = client
                .target(BASE_URL)
                .path(RESOURCE_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(category, MediaType.APPLICATION_JSON));

        RestResponse restResponse = response.readEntity(RestResponse.class);
        return RestResponse.responseBuilder(
                restResponse.isSuccess(),
                restResponse.getCode(),
                restResponse.getMessage(),
                restResponse.getResult()
        );
    }

    public List<Category> getAllCategories() {
        Response response = client
                .target(BASE_URL)
                .path(RESOURCE_URL)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Error retrieving categories. Status: " + response.getStatus());
        }

        List<Category> categoryLists = response.readEntity(new GenericType<List<Category>>() {
        });

        return categoryLists;
    }
}



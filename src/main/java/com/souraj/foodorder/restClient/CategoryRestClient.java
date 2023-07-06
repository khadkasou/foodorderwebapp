/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.restClient;

import com.souraj.foodorder.model.Category;
import java.util.List;
import javax.enterprise.context.RequestScoped;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

@RequestScoped
public class CategoryRestClient {

    private static final String API_BASE_URL = "http://10.120.3.176:8080/foodorderwebapp-1.0-SNAPSHOT/api";
    private static final String CATEGORY_RESOURCE_URL = "/category";

    private final Client client;

    public CategoryRestClient() {
        client = ClientBuilder.newClient();
    }

    public List<Category> getAllCategories() {
        Response response = client
                .target(API_BASE_URL)
                .path(CATEGORY_RESOURCE_URL)
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Error retrieving categories. Status: " + response.getStatus());
        }

        List<Category> categoryList
                = (List<Category>) response.readEntity(Category.class);
        return categoryList;
    }

    public Category getCategoryById(Long id) {
        Response response = client
                .target(API_BASE_URL)
                .path(CATEGORY_RESOURCE_URL)
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .get();

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Error retrieving category. Status: " 
                    + response.getStatus());
        }

        Category category = response.readEntity(Category.class);
        return category;
    }

    public void createCategory(Category category) {
        Response response = client
                .target(API_BASE_URL)
                .path(CATEGORY_RESOURCE_URL)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(category,
                        MediaType.APPLICATION_JSON));

        if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
            throw new RuntimeException("Error creating category. Status: "
                    + response.getStatus());
        }
        
    }

    public void updateCategory(Category category) {
        Response response = client
                .target(API_BASE_URL)
                .path(CATEGORY_RESOURCE_URL)
                .path(String.valueOf(category.getId()))
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(category,
                        MediaType.APPLICATION_JSON));

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            throw new RuntimeException("Error updating category. Status: "
                    + response.getStatus());
        }
    }

    public void deleteCategory(Long id) {
        Response response = client.target(API_BASE_URL)
                .path(CATEGORY_RESOURCE_URL)
                .path(String.valueOf(id))
                .request(MediaType.APPLICATION_JSON)
                .delete();

        if (response.getStatus() != Response.Status.NO_CONTENT.getStatusCode()) {
            throw new RuntimeException("Error deleting category. Status: "
                    + response.getStatus());
        }
    }
}

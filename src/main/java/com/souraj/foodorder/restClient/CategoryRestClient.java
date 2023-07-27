///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.souraj.foodorder.restClient;
//
///**
// *
// * @author ksouraj
// */
//import com.souraj.foodorder.model.Category;
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.net.HttpURLConnection;
//import java.net.URL;
//
//public class CategoryRestClient {
//    private static final String API_URL = "http://10.120.3.176:8080/foodorderwebapp-1.0-SNAPSHOT/api/category";
//
//    public static void main(String[] args) {
//        
//        // Retrieve all categories
//           retrieveAllCategories();
//
//        // Create a new category
//        Category newCategory = new Category();
//        createCategory(newCategory);
//
//        // Update an existing category
//        Category updatedCategory = new Category();
//        updateCategory(newCategory.getId(), updatedCategory);
//
//        // Delete a category
//        deleteCategory(newCategory.getId());
//    }
//
//    private static void retrieveAllCategories() {
//        try {
//            URL url = new URL(API_URL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("GET");
//            int responseCode = connection.getResponseCode();
//            System.out.println("GET All Categories - Response Code: " + responseCode);
//            if (responseCode == HttpURLConnection.HTTP_OK) {
//                StringBuilder response;
//                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
//                    String line;
//                    response = new StringBuilder();
//                    while ((line = reader.readLine()) != null) {
//                        response.append(line);
//                    }
//                }
//                System.out.println("GET All Categories - Response Body: " + response.toString());
//            }
//            connection.disconnect();
//        } catch (IOException e) {
//        }
//    }
//
//    private static void createCategory(Category category) {
//        try {
//            URL url = new URL(API_URL);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setDoOutput(true);
//            String jsonBody = "{\"name\":\"" + category.getName() + "\"}";
//            try (OutputStream outputStream = connection.getOutputStream()) {
//                outputStream.write(jsonBody.getBytes());
//                outputStream.flush();
//            }
//            int responseCode = connection.getResponseCode();
//            System.out.println("Create Category - Response Code: " + responseCode);
//            connection.disconnect();
//        } catch (IOException e) {
//        }
//    }
//
//    private static void updateCategory(Long id, Category category) {
//        try {
//            URL url = new URL(API_URL + "/" + id);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("PUT");
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setDoOutput(true);
//            String jsonBody = "{\"name\":\"" + category.getName() + "\"}";
//            try (OutputStream outputStream = connection.getOutputStream()) {
//                outputStream.write(jsonBody.getBytes());
//                outputStream.flush();
//            }
//            int responseCode = connection.getResponseCode();
//            System.out.println("Update Category - Response Code: " + responseCode);
//            connection.disconnect();
//        } catch (IOException e) {
//        }
//    }
//
//    private static void deleteCategory(Long id) {
//        try {
//            URL url = new URL(API_URL + "/" + id);
//            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//            connection.setRequestMethod("DELETE");
//            int responseCode = connection.getResponseCode();
//            System.out.println("Delete Category - Response Code: " + responseCode);
//            connection.disconnect();
//        } catch (IOException e) {
//        }
//    }
//}
//

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.api;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestResponse {

    private boolean success;
    private String code;
    private String message;
    private JsonObject result;

    public RestResponse() {
    }

    public RestResponse(boolean success, String code, String message, JsonObject result) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JsonObject getResult() {
        return result;
    }

    public void setResult(JsonObject result) {
        this.result = result;
    }

    public static Response responseBuilder(boolean success, String code, String message, JsonObject result) {
        JsonObject json = Json.createObjectBuilder()
                .add("success", success)
                .add("code", code)
                .add("message", message)
                .add("result", result == null ? Json.createObjectBuilder().build() : result)
                .build();

        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(json)
                .build();
    }
}

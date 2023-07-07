/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.api;

import java.util.HashMap;
import java.util.Map;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestResponse {

    private boolean success;
    private String code;
    private String message;
    private Object result;

    public RestResponse() {
    }

    public RestResponse(boolean success, String code, String message, Object result) {
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

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public static Response responseBuilder(boolean success, String code, String message, Object result) {
        Map<String,Object> map = new HashMap<>();
                map.put("success", success);
                map.put("code", code);
                map.put("message", message);
                map.put("result", result);
                

        return Response.status(Response.Status.OK)
                .type(MediaType.APPLICATION_JSON)
                .entity(map)
                .build();
    }
}

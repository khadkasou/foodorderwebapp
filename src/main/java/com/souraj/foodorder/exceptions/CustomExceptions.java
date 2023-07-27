/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.exceptions;

/**
 *
 * @author ksouraj
 */

public class CustomExceptions extends RuntimeException{
    
    public CustomExceptions(){
        super();
    }
    
    
     public CustomExceptions(String message) {
        super(message);
    }
    
}

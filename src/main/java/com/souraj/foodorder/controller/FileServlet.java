/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.souraj.foodorder.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author synergy
 */
@WebServlet(name = "FileServlet", urlPatterns = {"/FileServlet"})
public class FileServlet extends HttpServlet {

   private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String fileName = request.getParameter("filename");
        String filePath = "/home/synergy/Uploads/Files/" + fileName; 

        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);

        response.setContentType("application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        OutputStream os = response.getOutputStream();
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.flush();
        os.close();
        fis.close();
    }

    
   

}

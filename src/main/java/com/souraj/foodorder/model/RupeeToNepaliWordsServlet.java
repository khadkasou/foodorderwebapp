///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.souraj.foodorder.model;
//
///**
// *
// * @author ksouraj
// */
//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class RupeeToNepaliWordsServlet extends HttpServlet {
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        // Read the rupee value from the user input
//        String rupeeString = request.getParameter("rupee");
//        int rupee = Integer.parseInt(rupeeString);
//
//        // Convert the rupee value to Nepali words
//        String nepaliWords = convertToNepaliWords(rupee);
//
//        // Set the Nepali words as a request attribute
//        request.setAttribute("nepaliWords", nepaliWords);
//
//        // Forward the request to a JSP page for displaying the result
//        request.getRequestDispatcher("result.jsp").forward(request, response);
//    }
//
//    private String convertToNepaliWords(int rupee) {
//        // Array containing Nepali words for numbers from 1 to 19
//        String[] words = {
//            "", "एक", "दुई", "तीन", "चार", "पाँच", "छ", "सात", "आठ", "नौन", "दश",
//            "एघार", "बाह्र", "तेह्र", "चौध", "पन्ध्र", "सोह्र", "सत्र", "अठार", "उन्नाइस"
//        };
//
//        if (rupee == 0) {
//            return "शुन्य";
//        } else if (rupee < 20) {
//            return words[rupee];
//        } else if (rupee < 100) {
//            int tens = rupee / 10;
//            int units = rupee % 10;
//            return words[tens] + "बीस" + (units != 0 ? " " + words[units] : "");
//        } else if (rupee < 1000) {
//            int hundreds = rupee / 100;
//            int remainder = rupee % 100;
//            return words[hundreds] + "सय" + (remainder != 0 ? " " + convertToNepaliWords(remainder) : "");
//        } else {
//            int thousands = rupee / 1000;
//            int remainder = rupee % 1000;
//            return convertToNepaliWords(thousands) + " हजार" + (remainder != 0 ? " " + convertToNepaliWords(remainder) : "");
//        }
//    }
//}
//

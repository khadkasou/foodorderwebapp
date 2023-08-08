///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.souraj.foodorder.utils;
//
//import com.souraj.foodorder.exceptions.CustomException;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.enterprise.context.RequestScoped;
//import javax.inject.Inject;
//
//import org.apache.commons.io.FileUtils;
//
///**
// *
// * @author ksouraj
// */
//@RequestScoped
//public class FileUtills {
//    
//    @Inject
//    transient Logger log;
//
//    public String getFullPath(String basePath, String subPath, String fileName) throws CustomException{
//        if (basePath == null || basePath.equals("") || basePath.isEmpty()) {
//            throw new CustomException("File Base Path is not defined !!!");
//        }
//
//        String fullPath = basePath;
//        if (subPath != null && !basePath.equals("") && !basePath.isEmpty()) {
//            fullPath = fullPath.concat(subPath);
//        }
//        if (fileName != null && !fileName.equals("") && !fileName.isEmpty()) {
//            fullPath = fullPath.concat(fileName);
//        }
//        return fullPath;
//    }
//    
//    public void removeDirectory(String basePath, String subPath) throws CustomException, IOException {
//        try {
//            FileUtils.deleteDirectory(new File(getFullPath(basePath, subPath, null)));
//        } catch (IOException e) {
//            log.log(Level.SEVERE, "Failed to remove directory: " + basePath.concat("/").concat(subPath), e);
//            throw new CustomException("Failed to remove directory !!!");
//        }
//    }
//
//    public void createDirectoryIfNotExists(String dir) {
//        File directory = new File(dir);
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }
//    }
//    
//    
//     public void addFile(String basePath, String subPath, String fileName, byte[] file) throws CustomException {
//        try {
//            File directory = new File(basePath.concat(subPath) + File.separator);
//            if (!directory.exists()) {
//                directory.mkdirs();
//            }
//            String fullPath = getFullPath(basePath, subPath, null);
//            OutputStream out = new FileOutputStream(new File(fullPath, fileName));
//            out.write(file);
//            out.close();
//        } catch (IOException e) {
//            log.log(Level.SEVERE, "Failed to add file: " + basePath.concat("/").concat(subPath), e);
//            throw new CustomException("Failed to add document !!!");
//        }
//    }   
//    
//     
//      public void removeFile(String basePath, String subPath, String fileName) throws CustomException {
//        try {
//            File storedFile = new File(getFullPath(basePath, subPath, fileName));
//            storedFile.delete();
//        } catch (CustomException e) {
//            log.log(Level.SEVERE, "Error while removing file " + basePath.concat("/").concat(subPath).concat("/").concat(fileName), e);
//            throw new CustomException("Failed to remove file !!!");
//        }
//    }
//      
//      
//      public InputStream getInputStream(String basePath, String subPath, String fileName) throws CustomException {
//        try {
//            File file = new File(basePath.concat(subPath).concat("/").concat(fileName));
//            InputStream inputStream = new FileInputStream(file);
//            return inputStream;
//        } catch (IOException e) {
//            log.log(Level.SEVERE, "Error while getting file " + basePath.concat(subPath).concat("/").concat(fileName), e);
//            throw new CustomException("Failed to get file !!!");
//        }
//    }
//      
//      
//       public byte[] getByteArray(String basePath, String subPath, String fileName) throws CustomException {
//        try {
//            File file = new File(basePath.concat(subPath).concat("/").concat(fileName));
//            byte[] data = FileUtils.readFileToByteArray(file);
//            return data;
//        } catch (IOException e) {
//            log.log(Level.SEVERE, "Error while getting byte array " + basePath.concat(subPath).concat("/").concat(fileName), e);
//            throw new CustomException("Failed to get file !!!");
//        }
//    }
//     
//}

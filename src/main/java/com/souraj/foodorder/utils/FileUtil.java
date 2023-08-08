/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.IOUtils;

public class FileUtil {
    public void saveFile(String uploadFolderPath, String fileName, InputStream input) throws IOException {
        byte[] fileBytes = IOUtils.toByteArray(input);
        Path fileToSave = Paths.get(uploadFolderPath, fileName);
        Files.write(fileToSave, fileBytes);
    }
}
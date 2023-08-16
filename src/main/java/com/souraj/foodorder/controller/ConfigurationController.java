/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Configuration;
import com.souraj.foodorder.repository.ConfigurationRepository;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author ksouraj
 */
@ManagedBean(name = "configurationController")
@SessionScoped
public class ConfigurationController {

    private Configuration configuration;
    private List<Configuration> configurationsList;
    private List<String> allowedTypesList;
    @Inject
    private ConfigurationRepository configurationRepository;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public List<Configuration> getConfigurationsList() {
        return configurationsList;
    }

    public void setConfigurationsList(List<Configuration> configurationsList) {
        this.configurationsList = configurationsList;
    }

    public List<String> getAllowedTypesList() {
        return allowedTypesList;
    }

    public void setAllowedTypesList(List<String> allowedTypesList) {
        this.allowedTypesList = allowedTypesList;
    }
    

    @PostConstruct
    public void init() {
        this.configuration = new Configuration();
        loadData();
        loadAllowedTypes();
    }

    public void beforeCreate() {
        this.configuration = new Configuration();
    }

    public void beforeUpdate(Configuration con) {
        this.configuration = configurationRepository.findById(con.getId());
    }

    public void saveData() {

        configurationRepository.save(configuration);
        loadData();
        this.configuration = new Configuration();
    }

    public void update() {
        configurationRepository.update(this.configuration);
        loadData();
        this.configuration = new Configuration();
    }

    public void delete(Long id) {
        configurationRepository.delete(id);
        loadData();
    }

    public void loadData() {
        this.configurationsList = new ArrayList<>();
        this.configurationsList = configurationRepository.findAll();
    }

    public void loadAllowedTypes() {
        this.allowedTypesList = new ArrayList<>();
        this.allowedTypesList.add(".jpg");
        this.allowedTypesList.add(".jpeg");
        this.allowedTypesList.add(".png");
        this.allowedTypesList.add(".pdf");
        this.allowedTypesList.add(".xls");     
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.souraj.foodorder.controller;

import com.souraj.foodorder.model.Configuration;
import com.souraj.foodorder.repository.ConfigurationRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;



/**
 *
 * @author ksouraj
*/

@Named(value = "configurationController")
@ViewScoped
public class ConfigurationController implements Serializable{

    private Configuration configuration;
    private List<Configuration> configurationsList;

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
    @PostConstruct
    public void init() {
        this.configuration = new Configuration();
        loadData();
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
    
  
    
    

}

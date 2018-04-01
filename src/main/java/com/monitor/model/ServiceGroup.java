package com.monitor.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class ServiceGroup implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -5790389715015280228L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    


    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    private List<ServiceEntity> services;


    

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public List<ServiceEntity> getServiceEntity() {
        return services;
    }


    public void setServiceEntity(List<ServiceEntity> services) {
        this.services = services;
    }


    public Long getId() {
        // TODO Auto-generated method stub
        return id;
    }
    
    public void setId(long id){
        this.id=id;
    }





 
}

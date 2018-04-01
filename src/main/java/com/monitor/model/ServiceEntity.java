package com.monitor.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monitor.dto.ServiceGroupResponse;


@Entity
public class ServiceEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -5401974049877266247L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private Information information;

    private Time lastLoggedIn;

    private String name;

    @Enumerated(EnumType.STRING)
    private  Status status;

    private String title;

    @JsonIgnore
    public String url;

    
    //(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ManyToOne
    (cascade = {CascadeType.ALL},fetch= FetchType.EAGER)
    @JsonIgnore
    private ServiceGroup serviceGroup;



    public ServiceGroup getServiceGroup() {
        return serviceGroup;
    }


    public void setServiceGroup(ServiceGroup serviceGroup) {
        this.serviceGroup = serviceGroup;
    }

    void setId(long id) {
        this.id = id;
    }


    public long getId() {
        return id;
    }


    public void setTime(Time lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }


    public Time getTime() {
        return lastLoggedIn;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }


    public void setUrl(String url) {
        this.url = url;
    }


    public String getUrl() {
        return url;
    }


    public  Status getStatus() {
        return status;
    }


    public  void setStatus(Status status) {
        this.status = status;
    }


    public void setInformation(Information information) {
        this.information = information;
    }


    public Information getInformation() {
        return information;
    }





}

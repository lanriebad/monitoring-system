package com.monitor.dto;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monitor.model.Information;
import com.monitor.model.ServiceGroup;
import com.monitor.model.Status;

public class ServiceObj {
    
    @Enumerated(EnumType.STRING)
    private Information information;

    private Time lastLoggedIn;

    private String name;

    @Enumerated(EnumType.STRING)
    private Status status;

    private String title;

    @JsonIgnore
    public String url;
    
    @JsonIgnore
    private List<ServiceGroup> serviceGroups = new ArrayList<>();

    public List<ServiceGroup> getServiceGroups() {
        return serviceGroups;
    }


    public void setServiceGroups(List<ServiceGroup> serviceGroups) {
        this.serviceGroups = serviceGroups;
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


    public Status getStatus() {
        return status;
    }


    public void setStatus(Status status) {
        this.status = status;
    }


    public void setInformation(Information information) {
        this.information = information;
    }


    public Information getInformation() {
        return information;
    }
}



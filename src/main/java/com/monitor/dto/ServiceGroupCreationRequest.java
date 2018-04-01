package com.monitor.dto;

import java.sql.Time;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.monitor.model.Information;
import com.monitor.model.ServiceEntity;
import com.monitor.model.Status;


public class ServiceGroupCreationRequest {

    @Enumerated(EnumType.STRING)
    private Information checkInfo;

    @Enumerated(EnumType.STRING)
    private Status checkUrl;

    private Time lastLoggedIn;

    private String name;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<ServiceEntity> serviceEntitys;

    private String title;

    @JsonIgnore
    public String url;


    public Information getInformation() {
        return checkInfo;
    }


    public Time getLastLoggedIn() {
        return lastLoggedIn;
    }


    public String getName() {
        return name;
    }


    public List<ServiceEntity> getServiceEntitys() {
        return serviceEntitys;
    }


    public Status getStatus() {
        return checkUrl;
    }


    public String getTitle() {
        return title;
    }


    public void getTitle(String title) {
        this.title = title;
    }


    public String getUrl() {
        return url;
    }


    public void ServiceEntitys(List<ServiceEntity> serviceEntitys) {
        this.serviceEntitys = serviceEntitys;
    }


    public void setInformation(Information checkInfo) {
        this.checkInfo = checkInfo;
    }


    public void setLastLoggedIn(Time lastLoggedIn) {
        this.lastLoggedIn = lastLoggedIn;
    }


    public void setName(String name) {
        this.name = name;
    }


    public void setStatus(Status checkUrl) {
        this.checkUrl = checkUrl;
    }


    public void setUrl(String url) {
        this.url = url;
    }
}

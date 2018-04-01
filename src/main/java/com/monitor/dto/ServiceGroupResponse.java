package com.monitor.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ServiceGroupResponse {

    private String name;
    
    private long groupId;
    
    @JsonProperty("services")
    private List<UrlStatus> services;
    
    
    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }
    
    public long getGroupId(){
        return groupId;
    }


    public void setName(String name) {
        this.name = name;
    }
    
    public String getName(){
        return name;
    }

    public void setService(List<UrlStatus> services) {
      this.services = services;
    }
    
    
    public List<UrlStatus> getServices(){
        return services;
    }

  
}

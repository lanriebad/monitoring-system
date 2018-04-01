package com.monitor.service;

import java.util.List;

import com.monitor.dto.ServiceGroupResponse;
import com.monitor.dto.UrlStatus;
import com.monitor.model.ServiceEntity;
import com.monitor.model.ServiceGroup;


public interface MonitoringService {

    void addServiceGroups(ServiceGroupResponse serviceGroupResponse) throws Exception;


    void addServices(UrlStatus urlStatus) throws Exception;


    List<ServiceEntity> findAll();


    List<ServiceGroup> findAllServiceGroup();


    List<ServiceEntity> findByName(String url);
}

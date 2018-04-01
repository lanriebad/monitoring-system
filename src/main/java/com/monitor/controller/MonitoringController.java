package com.monitor.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monitor.dto.DefaultServiceResponse;
import com.monitor.dto.ServiceGroupResponse;
import com.monitor.dto.ServiceResponse.ResponseCode;
import com.monitor.dto.UrlStatus;
import com.monitor.model.ServiceEntity;
import com.monitor.model.ServiceGroup;
import com.monitor.service.MonitoringService;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class MonitoringController {

    
    @Autowired
    private MonitoringService monitoringService;


    @Autowired
    public MonitoringController(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }


    @RequestMapping(value = "monitoring/url", method = RequestMethod.GET)
    DefaultServiceResponse<? extends Serializable> findOneUrl(@RequestParam String url) {
        DefaultServiceResponse defaultServiceResponse = new DefaultServiceResponse<>();
        List<ServiceEntity> monitoring = monitoringService.findByName("%" + url + "%");
        ResponseCode responseCode;
        if (monitoring != null) {
            defaultServiceResponse.setResponseData(Collections.singletonList(monitoring));
            responseCode = ResponseCode.SUCCESS;
        } else {
            responseCode = ResponseCode.ERROR;
        }
        defaultServiceResponse.setResponseCode(responseCode.getCode());
        defaultServiceResponse.setResponseMsg(responseCode.getCode());
        return defaultServiceResponse;
    }


    @RequestMapping(value = "monitoring", method = RequestMethod.GET)
    DefaultServiceResponse<? extends Serializable> getAllUrl() {
        DefaultServiceResponse defaultServiceResponse = new DefaultServiceResponse<>();
        List<ServiceEntity> monitoring = monitoringService.findAll();
        ResponseCode responseCode;
        if (monitoring != null) {
            Map<String, List<ServiceEntity>> d = monitoring.stream().collect(Collectors.groupingBy(m -> m.getServiceGroup().getName()));
            List<ServiceGroupResponse> serviceGroupResponses = new ArrayList<>();
            d.forEach((k, v) -> {
                ServiceGroupResponse groupResponse = new ServiceGroupResponse();
                List<UrlStatus> serviceRequests = new ArrayList<>();
                groupResponse.setName(k);
                groupResponse.setService(serviceRequests);
                v.stream().forEach(service -> {
                    UrlStatus creationRequest = new UrlStatus();
                    creationRequest.setName(service.getName());
                    creationRequest.setStatus(service.getStatus());
                    creationRequest.setDate(service.getTime());
                    creationRequest.setInformation(service.getInformation());
                    creationRequest.setTitle(service.getTitle());
                    serviceRequests.add(creationRequest);
                });
                serviceGroupResponses.add(groupResponse);
            });
            defaultServiceResponse.setResponseData(serviceGroupResponses);
            responseCode = ResponseCode.SUCCESS;
        } else {
            responseCode = ResponseCode.ERROR;
        }
        defaultServiceResponse.setResponseCode(responseCode.getCode());
        defaultServiceResponse.setResponseMsg(responseCode.getCode());
        return defaultServiceResponse;
    }
    
    
    
    @RequestMapping(value="service", method=RequestMethod.POST)
    public DefaultServiceResponse <? extends Serializable> createService(@RequestBody UrlStatus urlStatus) throws Exception{
        DefaultServiceResponse <ServiceEntity> defaultServiceResponse= new DefaultServiceResponse<>();
        ResponseCode responseCode;
        responseCode = ResponseCode.SUCCESS;
        defaultServiceResponse.setResponseCode(responseCode.getCode());
        defaultServiceResponse.setResponseMsg(responseCode.getCode());
        monitoringService.addServices(urlStatus);
        return defaultServiceResponse;
    }

    
    @RequestMapping(value="serviceGroup", method=RequestMethod.POST)
    public DefaultServiceResponse <? extends Serializable> createServiceGroup(@RequestBody ServiceGroupResponse serviceGroupResponse) throws Exception{
        DefaultServiceResponse <ServiceGroup> defaultServiceResponse= new DefaultServiceResponse<>();
        ResponseCode responseCode;
        responseCode = ResponseCode.SUCCESS;
        defaultServiceResponse.setResponseCode(responseCode.getCode());
        defaultServiceResponse.setResponseMsg(responseCode.getCode());
        monitoringService.addServiceGroups(serviceGroupResponse);
        return defaultServiceResponse;
    }
}

package com.monitor.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monitor.dto.ServiceGroupResponse;
import com.monitor.dto.UrlStatus;
import com.monitor.model.Information;
import com.monitor.model.ServiceEntity;
import com.monitor.model.ServiceGroup;
import com.monitor.model.Status;
import com.monitor.repository.MonitoringRepository;
import com.monitor.repository.ServiceGroupRepository;


@Service("monitoringService")
public class MonitoringServiceImpl implements MonitoringService {

    public static Information checkInfo(String url) {
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            if (code == 200) {
                return Information.ASSIGNED;
            }
        } catch (Exception e) {
            return Information.UNASSIGNED;
        }
        return Information.UNASSIGNED;
    }


    public static Status checkUrl(String url) throws IOException {
        try {
            URL siteURL = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) siteURL.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int code = connection.getResponseCode();
            if (code == 200) {
                return Status.WARNING;
            }
        } catch (Exception e) {
            return Status.DANGER;
        }
        return Status.DANGER;
    }


    @Autowired
    MonitoringRepository monitoringRepository;

    @Autowired
    ServiceGroupRepository serviceGroupRepository;


    @Override
    public void addServiceGroups(ServiceGroupResponse serviceGroupResponse) throws Exception {
        ServiceGroup serviceGroup = new ServiceGroup();
        serviceGroup.setName(serviceGroupResponse.getName());
        serviceGroupRepository.save(serviceGroup);
    }


    @Override
    public void addServices(UrlStatus urlStatus) throws Exception {
        ServiceEntity serviceEntity = new ServiceEntity();
        ServiceGroup serviceGroup = new ServiceGroup();
        serviceGroup.setId(urlStatus.getGroupId());
        serviceEntity.setName(urlStatus.getName());
        serviceEntity.setTitle(urlStatus.getTitle());
        serviceEntity.setServiceGroup(serviceGroup);
        monitoringRepository.save(serviceEntity);
    }


    @Override
    public List<ServiceEntity> findAll() {
        this.monitoringRepository.findAll().stream().forEach(m -> {
            ServiceEntity serviceEntity = new ServiceEntity();
            m.setStatus(ScheduledTasks.checkUrl(m.url));
            m.setInformation(ScheduledTasks.checkInfo(m.url));
            serviceEntity.setTime(m.getTime());
            serviceEntity.setTitle(m.getTitle());
            serviceEntity.setName(m.getName());
        });
        return monitoringRepository.findAll();
    }


    @Override
    public List<ServiceGroup> findAllServiceGroup() {
        return serviceGroupRepository.findAll();
    }


    @Override
    public List<ServiceEntity> findByName(String url) {
        this.monitoringRepository.findByName("%" + url + "%").stream().forEach(m -> {
            m.setStatus(ScheduledTasks.checkUrl(m.url));
            m.setInformation(ScheduledTasks.checkInfo(m.url));
        });
        ;
        return monitoringRepository.findByName("%" + url + "%");
    }
}

package com.monitor.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.monitor.model.Information;
import com.monitor.model.Status;
import com.monitor.repository.MonitoringRepository;


@Component
public class ScheduledTasks {

    
    private MonitoringRepository monitoringRepository;
    
    @Autowired
    public ScheduledTasks(MonitoringRepository monitoringRepository) {
        this.monitoringRepository = monitoringRepository;
    }


    @Scheduled(fixedRate = 2000)
    public  void scheduleTaskWithFixedRate() {
        this.monitoringRepository.findAll().stream().forEach(m -> {
            m.setStatus(ScheduledTasks.checkUrl(m.url));
            m.setInformation(ScheduledTasks.checkInfo(m.url));
        });
    }


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


    public static Status checkUrl(String url) {
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
    
}

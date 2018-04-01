package com.monitor.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.monitor.model.ServiceEntity;


@Repository
public interface MonitoringRepository extends JpaRepository<ServiceEntity, Serializable> {

    @Query("select m from ServiceEntity m where m.url Like :url ")
    List<ServiceEntity> findByName(@Param(value = "url") String url);
}

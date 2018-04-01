package com.monitor.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monitor.model.ServiceGroup;


@Repository
public interface ServiceGroupRepository extends JpaRepository<ServiceGroup, Serializable> {
}

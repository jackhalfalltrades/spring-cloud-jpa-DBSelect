package com.spring.cloud.jpa.repository;

import com.spring.cloud.jpa.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, String> {
}

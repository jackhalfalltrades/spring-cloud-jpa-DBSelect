package com.maat.bestbuy.integration.repository;

import com.maat.bestbuy.integration.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, String> {
}

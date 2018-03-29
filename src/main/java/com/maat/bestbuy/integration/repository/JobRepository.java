package com.maat.bestbuy.integration.repository;

import com.maat.bestbuy.integration.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, String> {

    @Query(value= "select j.* from job_master j where to_date(j.submitted_date,'DD/MM/YYYY') between to_date(:fromDate,'DD/MM/YYYY') and to_date(:toDate,'DD/MM/YYYY')", nativeQuery = true)
    List<Job> findJobDetailsbyDates(@Param("fromDate") String fromDate, @Param("toDate") String toDate);

    @Query(value= "select j.* from job_master j where job_status = :jobStatus", nativeQuery = true)
    List<Job> queryJobByStatus(@Param("jobStatus") String jobStatus);

    @Query(value= "select j.* from job_master j where job_status not in (:jobStatus)", nativeQuery = true)
    List<Job> queryJobNotInStatus(@Param("jobStatus") String jobStatus);
}


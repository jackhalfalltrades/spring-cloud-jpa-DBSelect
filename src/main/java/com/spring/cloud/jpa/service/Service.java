package com.spring.cloud.jpa.service;

import com.spring.cloud.jpa.model.Job;
import com.spring.cloud.jpa.model.JobDetails;
import com.spring.cloud.jpa.model.Payload;
import rx.Observable;

import java.util.List;

public interface Service {
    List<Job> fetchJobDetails(Payload payload);

    Observable<List<JobDetails>> jobSummaryByStatus(String jobStatus);

    List<JobDetails> jobSummary(Payload payload);

}

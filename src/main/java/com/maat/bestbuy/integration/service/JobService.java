package com.maat.bestbuy.integration.service;

import com.maat.bestbuy.integration.model.Job;
import com.maat.bestbuy.integration.model.JobDetails;
import com.maat.bestbuy.integration.model.Payload;

import java.util.List;
import rx.Observable;

public interface JobService {
    List<Job> fetchJobDetails(Payload payload);
    Observable<List<JobDetails>> jobSummaryByStatus(String jobStatus);
    List<JobDetails> jobSummary(Payload payload);

}

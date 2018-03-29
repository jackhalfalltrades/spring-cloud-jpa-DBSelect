package com.maat.bestbuy.integration.service;

import com.maat.bestbuy.integration.exception.InternalServerErrorException;
import com.maat.bestbuy.integration.exception.MAATRuntimeException;
import com.maat.bestbuy.integration.model.Job;
import com.maat.bestbuy.integration.model.JobDetails;
import com.maat.bestbuy.integration.model.Payload;
import com.maat.bestbuy.integration.repository.ApplicationRepository;
import com.maat.bestbuy.integration.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import rx.Observable;

@Service("jobService")
public class JobServiceImpl implements JobService {

    private ApplicationRepository applicationRepository;
    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, ApplicationRepository applicationRepository) {
        this.jobRepository = jobRepository;
        this.applicationRepository = applicationRepository;
    }

    @Override
    public List<Job> fetchJobDetails(Payload payload) {
        return null;
    }


    @Override
    public Observable<List<JobDetails>> jobSummaryByStatus(String jobStatus) {
        try {
            if (jobStatus.equalsIgnoreCase("new"))
                return Observable.just(jobRepository.queryJobByStatus(jobStatus).stream().map(e -> {
                    return JobDetails.builder().application(applicationRepository.findById(e.getApplication()).get().getAppName())
                            .createdBy(e.getCreatedBy()).createdAt(e.getCreatedTimeStamp().toString())
                            .requestNumber(e.getRequestNumber()).requestType(e.getRequestType())
                            .jobParam(new String(e.getParam()))
                            .jobStatus(e.getJobStatus())
                            .type(e.getJobType())
                            .id(e.getId()).build();
                }).collect(Collectors.toList()));

            return Observable.just(jobRepository.queryJobNotInStatus("New").stream().map(e -> {
                return JobDetails.builder().application(applicationRepository.findById(e.getApplication()).get().getAppName())
                        .createdBy(e.getCreatedBy()).createdAt(e.getCreatedTimeStamp().toString())
                        .requestNumber(e.getRequestNumber()).requestType(e.getRequestType())
                        .jobParam(new String(e.getParam()))
                        .jobStatus(e.getJobStatus())
                        .submittedBy(e.getSubmittedBy())
                        .submittedAt(e.getSubmittedTimeStamp().toString())
                        .type(e.getJobType())
                        .id(e.getId()).build();
            }).collect(Collectors.toList()));
        } catch(NullPointerException e) {
            throw new InternalServerErrorException(e.getLocalizedMessage(), new Object[] {e.getClass().getName(), e.getCause(), e});
        } catch(Exception e) {
            throw new MAATRuntimeException(e.getLocalizedMessage(), new Object[] {e.getClass().getName(), e.getCause(), e});
        }
    }

    @Override
    public List<JobDetails> jobSummary(Payload payload) {
        return jobRepository.findAll().stream().map(e -> {
            return JobDetails.builder().application(applicationRepository.findById(e.getApplication()).get().getAppName())
                    .createdBy(e.getCreatedBy()).createdAt(e.getCreatedTimeStamp().toString())
                    .requestNumber(e.getRequestNumber()).requestType(e.getRequestType())
                    .jobParam(new String(e.getParam()))
                    .jobStatus(e.getJobStatus())
                    .type(e.getJobType())
                    .id(e.getId()).build();
        }).collect(Collectors.toList());
    }
}

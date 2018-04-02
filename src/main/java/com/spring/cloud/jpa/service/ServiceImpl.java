package com.spring.cloud.jpa.service;

import com.spring.cloud.jpa.exception.InternalServerErrorException;
import com.spring.cloud.jpa.exception.JPARuntimeException;
import com.spring.cloud.jpa.model.Job;
import com.spring.cloud.jpa.model.JobDetails;
import com.spring.cloud.jpa.model.Payload;
import com.spring.cloud.jpa.repository.ApplicationRepository;
import com.spring.cloud.jpa.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import rx.Observable;

import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service("jobService")
public class ServiceImpl implements Service {

    private ApplicationRepository applicationRepository;
    private JobRepository jobRepository;

    @Autowired
    public ServiceImpl(JobRepository jobRepository, ApplicationRepository applicationRepository) {
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
        } catch (NullPointerException e) {
            throw new InternalServerErrorException(e.getLocalizedMessage(), new Object[]{e.getClass().getName(), e.getCause(), e});
        } catch (Exception e) {
            throw new JPARuntimeException(e.getLocalizedMessage(), new Object[]{e.getClass().getName(), e.getCause(), e});
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

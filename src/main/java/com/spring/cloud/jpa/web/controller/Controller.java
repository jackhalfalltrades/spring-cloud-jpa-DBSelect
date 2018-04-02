package com.spring.cloud.jpa.web.controller;

import com.spring.cloud.jpa.model.JobDetails;
import com.spring.cloud.jpa.model.Payload;
import com.spring.cloud.jpa.service.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    private ServiceImpl jobService;

    @Autowired
    public Controller(ServiceImpl jobService) {
        this.jobService = jobService;
    }

    /*  @GetMapping(value = "/jobDetails/{id}/{scheduledFromDate}/{scheduledToDate}", produces = MediaType.APPLICATION_JSON_VALUE)
      public @ResponseBody
      DeferredResult<List<Job>> getJobDetails(@RequestParam(required = false, value = "jobStatus", defaultValue = "new") String jobStatus, @Valid Payload payload) {
          payload.setId(jobStatus);
          LOGGER.debug("Job Details: {}", payload);
          List<Job> jobList = jobService.fetchJobDetails(payload);
          DeferredResult<List<Job>> deferredResult = new DeferredResult<>();
          deferredResult.setResult(jobList);
          return deferredResult;
      }*/
    @GetMapping(value = "/jobSummary/{jobStatus}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @CrossOrigin
    @ResponseBody
    DeferredResult<List<JobDetails>> getAllJobDetails(
            @RequestParam(required = false, value = "jobStatus", defaultValue = "new") String jobStatus, @Valid Payload payload) {

        LOGGER.debug("Job Details: {}", payload);
        Observable<List<JobDetails>> observable = jobService.jobSummaryByStatus(payload.getJobStatus());
        DeferredResult<List<JobDetails>> result = new DeferredResult<>();
        observable.subscribe(jobDetailList -> result.setResult(jobDetailList), error -> result.setErrorResult(error));
        return result;
    }
}

package com.spring.cloud.jpa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
public class JobDetails implements Serializable {

    private final static long serialVersionUID = 8125502179272231981L;

    @JsonProperty("jobID")
    private String id;
    @JsonProperty("jobType")
    private String type;
    private String jobParam;
    private String createdAt;
    private String requestType;
    private String requestNumber;
    private String application;
    private String createdBy;
    private String jobStatus;
    private String submittedBy;
    private String submittedAt;
}

package com.spring.cloud.jpa.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "APPLICATION_MASTER")
@NamedQuery(name = "find_all_applications", query = "select a from Application a")
@Data
public class Application {

    @Id
    @Column(name = "ID")
    private String appId;
    @Column(name = "NAME")
    private String appName;
    @Column(name = "MERLIN_CODE")
    private String appMerlinCode;
    @Column(name = "ARTIFACTORY_PATH")
    private String artifactoryPath;
}

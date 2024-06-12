package com.iktpreobuka.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.security.Views;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "userEntities"})
public class JobTitleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Employee.class)
    protected Integer id;

    @Version
    protected Integer version;

    @JsonView(Views.Employee.class)
    protected String jobTitleName;

    @JsonView(Views.Manager.class)
    protected String jobTitleDescription;
    @JsonView(Views.Ceo.class)
    protected Double sallary;

    @OneToMany(mappedBy = "jobTitle", cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
//    @JsonManagedReference //DTO takes care of this
    protected List<UserEntity> userEntities = new ArrayList<>();

    public JobTitleEntity() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getJobTitleName() {
        return this.jobTitleName;
    }

    public void setJobTitleName(String jobTitleName) {
        this.jobTitleName = jobTitleName;
    }

    public String getJobTitleDescription() {
        return this.jobTitleDescription;
    }

    public void setJobTitleDescription(String jobTitleDescription) {
        this.jobTitleDescription = jobTitleDescription;
    }

    public Double getSallary() {
        return this.sallary;
    }

    public void setSallary(Double sallary) {
        this.sallary = sallary;
    }

    public List<UserEntity> getUserEntities() {
        return this.userEntities;
    }

    public void setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
    }
}

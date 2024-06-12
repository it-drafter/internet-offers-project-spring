package com.iktpreobuka.project.entities.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.security.Views;

public class JobTitleDto {
    @JsonView(Views.Employee.class)
    protected Integer id;

    @JsonView(Views.Employee.class)
    protected String jobTitleName;

    @JsonView(Views.Manager.class)
    protected String jobTitleDescription;
    @JsonView(Views.Ceo.class)
    protected Double sallary;

    public JobTitleDto() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}

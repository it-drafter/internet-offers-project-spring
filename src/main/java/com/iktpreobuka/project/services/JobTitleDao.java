package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.JobTitleEntity;
import com.iktpreobuka.project.entities.dto.JobTitleDto;

public interface JobTitleDao {
    public Iterable<JobTitleEntity> getAllJobTitles();

    public JobTitleDto addNewJobTitle(JobTitleDto jobTitleDto);

    //    public JobTitleEntity modifyExistingJobTitle(String id, JobTitleEntity changedJobTitle);
//    public JobTitleEntity modifyExistingJobTitle(String id, JobTitleDto changedJobTitle);
    public JobTitleDto modifyExistingJobTitle(String id, JobTitleDto changedJobTitle);

    public JobTitleDto deleteJobTitleById(String id);
}

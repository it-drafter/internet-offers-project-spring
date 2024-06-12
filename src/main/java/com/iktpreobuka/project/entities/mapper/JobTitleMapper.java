package com.iktpreobuka.project.entities.mapper;

import com.iktpreobuka.project.entities.JobTitleEntity;
import com.iktpreobuka.project.entities.dto.JobTitleDto;
import org.springframework.stereotype.Component;

@Component
public class JobTitleMapper {
    public JobTitleDto toDtoFromJobTitleEntity(JobTitleEntity jobTitleEntity) {
        JobTitleDto jobTitleDto = new JobTitleDto();

        jobTitleDto.setJobTitleName(jobTitleEntity.getJobTitleName());
        jobTitleDto.setJobTitleDescription(jobTitleEntity.getJobTitleDescription());
        jobTitleDto.setId(jobTitleEntity.getId());
        jobTitleDto.setSallary(jobTitleEntity.getSallary());

        return jobTitleDto;
    }

    public JobTitleEntity toEntityFromJobTitleDto(JobTitleDto jobTitleDto) {
        JobTitleEntity jobTitleEntity = new JobTitleEntity();

        jobTitleEntity.setJobTitleName(jobTitleDto.getJobTitleName());
        jobTitleEntity.setJobTitleDescription(jobTitleDto.getJobTitleDescription());
        jobTitleEntity.setId(jobTitleDto.getId());
        jobTitleEntity.setSallary(jobTitleDto.getSallary());

        return jobTitleEntity;
    }
}

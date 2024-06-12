package com.iktpreobuka.project.services;

import com.iktpreobuka.project.entities.JobTitleEntity;
import com.iktpreobuka.project.entities.dto.JobTitleDto;
import com.iktpreobuka.project.entities.mapper.JobTitleMapper;
import com.iktpreobuka.project.repositories.JobTitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JobTitleDaoImpl implements JobTitleDao {
    @Autowired
    protected JobTitleRepository jobTitleRepository;

    @Autowired
    protected JobTitleMapper jobTitleMapper;


    @Override
    public Iterable<JobTitleEntity> getAllJobTitles() {
        return jobTitleRepository.findAll();
    }

    @Override
    public JobTitleDto addNewJobTitle(JobTitleDto jobTitleDto) {
        JobTitleEntity jobTitleEntity = jobTitleMapper.toEntityFromJobTitleDto(jobTitleDto);

        jobTitleRepository.save(jobTitleEntity);

        JobTitleDto jobTitleDtoResponse = jobTitleMapper.toDtoFromJobTitleEntity(jobTitleEntity);

        return jobTitleDtoResponse;
    }

    //without DTO:
//    @Override
//    public JobTitleEntity modifyExistingJobTitle(String id, JobTitleEntity changedJobTitle) {
//        JobTitleEntity jobTitleDb = jobTitleRepository.findById(Integer.parseInt(id)).orElse(null);
//
//        if (jobTitleDb != null) {
//            if (changedJobTitle.getJobTitleName() != null) {
//                jobTitleDb.setJobTitleName(changedJobTitle.getJobTitleName());
//            }
//
//            if (changedJobTitle.getJobTitleDescription() != null) {
//                jobTitleDb.setJobTitleDescription(changedJobTitle.getJobTitleDescription());
//            }
//
//            if (changedJobTitle.getSallary() != null) {
//                jobTitleDb.setSallary(changedJobTitle.getSallary());
//            }
//
//            jobTitleRepository.save(jobTitleDb);
//
//            return jobTitleDb;
//        }
//
//        return null;
//    }

    //With DTO:
    @Override
    public JobTitleDto modifyExistingJobTitle(String id, JobTitleDto changedJobTitle) {
        JobTitleEntity jobTitleDb = jobTitleRepository.findById(Integer.parseInt(id)).orElse(null);

        if (jobTitleDb != null) {
            if (changedJobTitle.getJobTitleName() != null) {
                jobTitleDb.setJobTitleName(changedJobTitle.getJobTitleName());
            }

            if (changedJobTitle.getJobTitleDescription() != null) {
                jobTitleDb.setJobTitleDescription(changedJobTitle.getJobTitleDescription());
            }

            if (changedJobTitle.getSallary() != null) {
                jobTitleDb.setSallary(changedJobTitle.getSallary());
            }

            jobTitleRepository.save(jobTitleDb);

            JobTitleDto jobTitleDtoResponse = jobTitleMapper.toDtoFromJobTitleEntity(jobTitleDb);

            return jobTitleDtoResponse;
        }

        return null;
    }

    @Override
    public JobTitleDto deleteJobTitleById(String id) {
        JobTitleEntity jobTitleDb = jobTitleRepository.findById(Integer.parseInt(id)).orElse(null);

        if (jobTitleDb != null) {
            jobTitleRepository.delete(jobTitleDb);

            JobTitleDto jobTitleDtoResponse = jobTitleMapper.toDtoFromJobTitleEntity(jobTitleDb);

//            return jobTitleDb;
            return jobTitleDtoResponse;
        }

        return null;
    }
}

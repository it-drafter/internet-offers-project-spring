package com.iktpreobuka.project.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.project.entities.JobTitleEntity;
import com.iktpreobuka.project.entities.dto.JobTitleDto;
import com.iktpreobuka.project.security.Views;
import com.iktpreobuka.project.services.JobTitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/project/jobTitles")
public class JobTitleController {
    @Autowired
    protected JobTitleDao jobTitleService;

    @GetMapping("/employee")
    @JsonView(Views.Employee.class)
    public Iterable<JobTitleEntity> getAllJobTitles() {
        return jobTitleService.getAllJobTitles();
    }

    @GetMapping("/manager")
    @JsonView(Views.Manager.class)
    public Iterable<JobTitleEntity> getAllJobTitlesForManager() {
        return jobTitleService.getAllJobTitles();
    }

    @GetMapping("/ceo")
    @JsonView(Views.Ceo.class)
    public Iterable<JobTitleEntity> getAllJobTitlesForCeo() {
        return jobTitleService.getAllJobTitles();
    }

    @PostMapping()
    public ResponseEntity<?> addNewJobTitle(@RequestBody JobTitleDto jobTitleDto) {
        return new ResponseEntity<JobTitleDto>(jobTitleService.addNewJobTitle(jobTitleDto), HttpStatus.OK);
    }

//    @PutMapping("/{id}")
//    public JobTitleEntity modifyExistingJobTitle(@PathVariable String id, @RequestBody JobTitleEntity changedJobTitle) {
//        return jobTitleService.modifyExistingJobTitle(id, changedJobTitle);
//    }

//    @PutMapping("/{id}")
//    public JobTitleDto modifyExistingJobTitle(@PathVariable String id, @RequestBody JobTitleDto changedJobTitle) {
//        return jobTitleService.modifyExistingJobTitle(id, changedJobTitle);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modifyExistingJobTitle(@PathVariable String id, @RequestBody JobTitleDto changedJobTitle) {
        return new ResponseEntity<JobTitleDto>(jobTitleService.modifyExistingJobTitle(id, changedJobTitle), HttpStatus.OK);
    }

//    @DeleteMapping("/{id}")
//    public JobTitleDto deleteJobTitleById(@PathVariable String id) {
//        return jobTitleService.deleteJobTitleById(id);
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteJobTitleById(@PathVariable String id) {
        return new ResponseEntity<JobTitleDto>(jobTitleService.deleteJobTitleById(id), HttpStatus.OK);
    }
}

package net.engineeringdigest.jobPortal.Controller;

import net.engineeringdigest.jobPortal.Entity.Job;
import net.engineeringdigest.jobPortal.Entity.Recruiter;
import net.engineeringdigest.jobPortal.Repository.RecruiterRepository;
import net.engineeringdigest.jobPortal.Service.JobService;
import net.engineeringdigest.jobPortal.Service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job")
public class JobController {
    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private JobService jobService;
    @Autowired
    private RecruiterService recruiterService;
    @PostMapping
    void addJob(@RequestBody Job job){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         String name = authentication.getName();
         Recruiter recruiter = recruiterRepository.findRecruiterByUsername(name);
         job.setRecruiterId(recruiter);
         job.setDateOfPosting(new java.util.Date());
         Job job1 = jobService.saveJob(job);
         recruiter.getRequirementOfRecruiter().add(job1);
         recruiterService.saveRecruiterWithJobs(recruiter);
     }
}
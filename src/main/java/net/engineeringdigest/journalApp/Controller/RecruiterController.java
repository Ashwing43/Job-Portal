package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.Candidate;
import net.engineeringdigest.journalApp.Entity.Job;
import net.engineeringdigest.journalApp.Entity.Recruiter;
import net.engineeringdigest.journalApp.Repository.RecruiterRepository;
import net.engineeringdigest.journalApp.Service.JobService;
import net.engineeringdigest.journalApp.Service.RecruiterService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private RecruiterService recruiterService;
    @Autowired
    private JobService jobService;
    @GetMapping
    public List<Recruiter> getAllRecruiter(){
        return recruiterService.getAllRecruiters();
    }

    @PostMapping
    public boolean saveRecruiter(@RequestBody Recruiter recruiter){
        recruiterService.saveRecruiter(recruiter);
        return true;
    }

    @PostMapping("/job/{myId}")
    public boolean saveJobEntry(@RequestBody Job job, @PathVariable ObjectId myId){
        Recruiter recruiter = recruiterService.getRecruiterById(myId);
        job.setRecruiterId(recruiter);
        jobService.saveJob(job);
        return true;
    }

    @GetMapping("/job/{myId}")
    public  List<Job> getJobByRecruiter(@PathVariable ObjectId myId){
        Recruiter recruiter = recruiterService.getRecruiterById(myId);
        return recruiter.getRequirementOfRecruiter();
    }
}
package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.Candidate;
import net.engineeringdigest.journalApp.Entity.Job;
import net.engineeringdigest.journalApp.Entity.Recruiter;
import net.engineeringdigest.journalApp.Repository.RecruiterRepository;
import net.engineeringdigest.journalApp.Service.JobService;
import net.engineeringdigest.journalApp.Service.RecruiterService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private RecruiterService recruiterService;
    @Autowired
    private JobService jobService;
    @GetMapping("/all")
    public ResponseEntity<List<Recruiter>> getAllRecruiter(){
        List<Recruiter> recruiters = recruiterService.getAllRecruiters();
        if(recruiters == null || recruiters.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recruiters, HttpStatus.OK);
    }

    @GetMapping("/id/{myId}")
    public Recruiter getRecruiterById(@PathVariable ObjectId myId) {
        return recruiterService.getRecruiterById(myId);
    }
    @PostMapping
    public boolean saveRecruiter(@RequestBody Recruiter recruiter){
        recruiterService.saveRecruiter(recruiter);
        return true;
    }

    @PostMapping("/job/id/{myId}")
    public boolean saveJobEntry(@RequestBody Job job, @PathVariable ObjectId myId) throws Exception {
        jobService.saveJobByRecruiterId(job, myId);
        return true;
    }

    @GetMapping("/job/id/{myId}")
    public  List<Job> getJobByRecruiter(@PathVariable ObjectId myId){
        Recruiter recruiter = recruiterService.getRecruiterById(myId);
        return recruiter.getRequirementOfRecruiter();
    }
}

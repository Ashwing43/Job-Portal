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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter")
public class RecruiterController {
    @Autowired
    private RecruiterService recruiterService;
    @Autowired
    private RecruiterRepository recruiterRepository;
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
    @PutMapping("/update")
    public boolean saveRecruiter(@RequestBody Recruiter recruiter){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Recruiter recruiter1 = recruiterRepository.findRecruiterByUsername(name);
        recruiter1.setPassword(recruiter.getPassword());
        recruiter1.setUsername(recruiter.getUsername());
        recruiter1.setMobile(recruiter.getMobile());
        recruiter1.getRequirementOfRecruiter().addAll(recruiter.getRequirementOfRecruiter());
        recruiter1.setCompanyName(recruiter.getCompanyName());
        recruiter1.getRoles().addAll(recruiter.getRoles());
        recruiter1.setEmail(recruiter.getEmail());
        recruiterService.saveRecruiter(recruiter1);
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

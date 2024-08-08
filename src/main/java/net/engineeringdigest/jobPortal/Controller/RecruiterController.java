package net.engineeringdigest.jobPortal.Controller;

import net.engineeringdigest.jobPortal.Entity.Job;
import net.engineeringdigest.jobPortal.Entity.Recruiter;
import net.engineeringdigest.jobPortal.Repository.RecruiterRepository;
import net.engineeringdigest.jobPortal.Service.JobService;
import net.engineeringdigest.jobPortal.Service.RecruiterService;
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

    @GetMapping
    public ResponseEntity<?> getRecruiterById() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Recruiter recruiter = recruiterService.findRecruiterByUsername(name);
        if(recruiter != null)
            return new ResponseEntity<>(recruiter, HttpStatus.FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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

    @GetMapping("/jobs")
    public  List<Job> jobsOfRecruiter(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Recruiter recruiter = recruiterService.findRecruiterByUsername(name);
        return recruiter.getRequirementOfRecruiter();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteRecruiter(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Recruiter recruiter = recruiterService.findRecruiterByUsername(name);
        recruiterService.deleteRecruiterById(recruiter.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

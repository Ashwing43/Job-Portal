package net.engineeringdigest.jobPortal.Controller;

import net.engineeringdigest.jobPortal.Entity.Candidate;
import net.engineeringdigest.jobPortal.Entity.Recruiter;
import net.engineeringdigest.jobPortal.Service.CandidateService;
import net.engineeringdigest.jobPortal.Service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/signup")
public class PublicController {
    @Autowired
    public CandidateService candidateService;
    @Autowired
    public RecruiterService recruiterService;
    @PostMapping("/candidate")
    public ResponseEntity<?> saveCandidate(@RequestBody Candidate candidate){
        candidateService.saveCandidate(candidate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/recruiter")
    public ResponseEntity<?> saveRecruiter(@RequestBody Recruiter recruiter){
        recruiterService.saveRecruiter(recruiter);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
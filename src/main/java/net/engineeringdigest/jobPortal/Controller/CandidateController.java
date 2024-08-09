package net.engineeringdigest.jobPortal.Controller;

import net.engineeringdigest.jobPortal.Entity.Candidate;
import net.engineeringdigest.jobPortal.Entity.Job;
import net.engineeringdigest.jobPortal.Repository.JobRepository;
import net.engineeringdigest.jobPortal.Service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    public CandidateService candidateService;

    @Autowired
    public JobRepository jobRepository;

    @GetMapping
    public ResponseEntity<?> getCandidate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Candidate candidate = candidateService.findCandidateByUsername(name);
        if(candidate != null)
            return new ResponseEntity<>(candidate, HttpStatus.FOUND);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateCandidate(@RequestBody Candidate candidate){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Candidate candidate1 = candidateService.findCandidateByUsername(name);
        candidate1.setGithub(candidate.getGithub());
        candidate1.setUsername(candidate.getUsername());
        candidate1.setPassword(candidate.getPassword());
        candidate1.setEmail(candidate.getEmail());
        candidate1.setMobile(candidate.getMobile());
        candidate1.setDegreeCgpa(candidate.getDegreeCgpa());
        candidate1.setSkills(candidate.getSkills());
        candidate1.setHscPercentage(candidate.getHscPercentage());
        candidate1.setLinkedIn(candidate.getLinkedIn());
        candidate1.setSscPercentage(candidate.getSscPercentage());
        candidate1.getRoles().addAll(candidate.getRoles());
        candidateService.saveCandidate(candidate1);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteCandidate(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        Candidate candidate1 = candidateService.findCandidateByUsername(name);
        candidateService.deleteCandidatebyId(candidate1.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/available-jobs")
    public List<Job> getJobs() {
        return jobRepository.findAll().stream().filter(Job::isStatus).collect(Collectors.toList());
    }
}
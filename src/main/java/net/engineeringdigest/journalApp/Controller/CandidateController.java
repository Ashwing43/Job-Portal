package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.Candidate;
import net.engineeringdigest.journalApp.Repository.CandidateRepository;
import net.engineeringdigest.journalApp.Service.CandidateService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    public CandidateService candidateService;

    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
}
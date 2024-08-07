package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.Candidate;
//import net.engineeringdigest.journalApp.Entity.Recruiter;
import net.engineeringdigest.journalApp.Repository.CandidateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Component
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;
    public PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public void saveCandidate(@RequestBody Candidate candidate) {
        candidate.setPassword(passwordEncoder.encode(candidate.getPassword()));
        if(candidate.getRoles().isEmpty())
            candidate.setRoles(Collections.singletonList("CANDIDATE"));
        candidateRepository.save(candidate);
    }
    public List<Candidate> getAllCandidates(){
        return candidateRepository.findAll();
    }
    public Candidate getCandidateById(@RequestBody ObjectId id){
        return candidateRepository.findCandidateById(id);
    }

    public Candidate findCandidateByUsername(String name) {
        return candidateRepository.findCandidateByUsername(name);
    }
}
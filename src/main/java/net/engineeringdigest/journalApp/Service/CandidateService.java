package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.Candidate;
import net.engineeringdigest.journalApp.Entity.Recruiter;
import net.engineeringdigest.journalApp.Repository.CandidateRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Component
public class CandidateService {
    @Autowired
    private CandidateRepository candidateRepository;

    public void saveCandidate(@RequestBody Candidate candidate) {
        candidateRepository.save(candidate);
    }
    public List<Candidate> getAllCandidates(){
        return candidateRepository.findAll();
    }
    public Candidate getCandidateById(@RequestBody ObjectId id){
        return candidateRepository.findCandidateById(id);
    }
}
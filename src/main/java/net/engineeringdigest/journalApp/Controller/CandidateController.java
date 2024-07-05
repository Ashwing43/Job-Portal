package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.Candidate;
import net.engineeringdigest.journalApp.Service.CandidateService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/candidate")
public class CandidateController {
    @Autowired
    public CandidateService candidateService;

    @GetMapping
    public List<Candidate> getAllCandidate(){
        return candidateService.getAllCandidates();
    }

    @GetMapping("/id/{myId}")
    public Candidate getCandidateById(@PathVariable ObjectId myId){
        return candidateService.getCandidateById(myId);
    }

    @PostMapping
    public ResponseEntity<?> saveCandidate(@RequestBody Candidate candidate){
        candidateService.saveCandidate(candidate);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}

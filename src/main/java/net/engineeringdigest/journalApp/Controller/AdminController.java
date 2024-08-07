package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.Candidate;
import net.engineeringdigest.journalApp.Service.CandidateService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

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
}

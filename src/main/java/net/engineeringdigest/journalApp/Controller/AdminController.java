package net.engineeringdigest.journalApp.Controller;

import net.engineeringdigest.journalApp.Entity.Candidate;
import net.engineeringdigest.journalApp.Entity.Recruiter;
import net.engineeringdigest.journalApp.Service.CandidateService;
import net.engineeringdigest.journalApp.Service.RecruiterService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    public RecruiterService recruiterService;
    @GetMapping("/all-candidates")
    public List<Candidate> getAllCandidate(){
        return candidateService.getAllCandidates();
    }

    @GetMapping("/id/{myId}")
    public Candidate getCandidateById(@PathVariable ObjectId myId){
        return candidateService.getCandidateById(myId);
    }

    @GetMapping("/all-recruiters")
    public ResponseEntity<List<Recruiter>> getAllRecruiter(){
        List<Recruiter> recruiters = recruiterService.getAllRecruiters();
        if(recruiters == null || recruiters.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(recruiters, HttpStatus.OK);
    }
}

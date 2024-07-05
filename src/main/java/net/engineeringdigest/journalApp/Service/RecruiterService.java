package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.Recruiter;
import net.engineeringdigest.journalApp.Repository.RecruiterRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Component
public class RecruiterService {
    @Autowired
    private RecruiterRepository recruiterRepository;

    @GetMapping
    public List<Recruiter> getAllRecruiters(){
        return recruiterRepository.findAll();
    }

    @PostMapping
    public void saveRecruiter(@RequestBody Recruiter recruiter){
        recruiterRepository.save(recruiter);
    }

    public Recruiter getRecruiterById(ObjectId myId) {
        return recruiterRepository.findRecruiterById(myId);
    }
}

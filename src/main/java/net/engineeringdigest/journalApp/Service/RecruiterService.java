package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.Recruiter;
import net.engineeringdigest.journalApp.Repository.RecruiterRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
@Component
public class RecruiterService {
    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Recruiter> getAllRecruiters(){
        return recruiterRepository.findAll();
    }

    public void saveRecruiter(@RequestBody Recruiter recruiter){
        recruiter.setPassword(passwordEncoder.encode(recruiter.getPassword()));
        if(recruiter.getRoles().isEmpty())
            recruiter.setRoles(Collections.singletonList("RECRUITER"));
        recruiterRepository.save(recruiter);
    }

    public Recruiter getRecruiterById(ObjectId myId) {
//        return recruiterRepository.findRecruiterById(myId);
        return recruiterRepository.findById(myId).orElse(null);

    }
}

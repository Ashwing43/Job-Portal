package net.engineeringdigest.jobPortal.Service;

import net.engineeringdigest.jobPortal.Entity.Job;
import net.engineeringdigest.jobPortal.Entity.Recruiter;
import net.engineeringdigest.jobPortal.Repository.JobRepository;
import net.engineeringdigest.jobPortal.Repository.RecruiterRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class RecruiterService {
    @Autowired
    private RecruiterRepository recruiterRepository;
    @Autowired
    private JobRepository jobRepository;
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
        return recruiterRepository.findById(myId).orElse(null);

    }

    public void saveRecruiterWithJobs(Recruiter recruiter) {
        recruiterRepository.save(recruiter);
    }
    public Recruiter findRecruiterByUsername(String username){
        return recruiterRepository.findRecruiterByUsername(username);
    }

    public void deleteRecruiterById(ObjectId id) {
        Optional<Recruiter> r = recruiterRepository.findById(id);
        Recruiter recruiter = r.get();
        List<Job> list = recruiter.getJobList();
        for(Job j : list){
            jobRepository.delete(j);
        }
        recruiterRepository.deleteById(id);
    }
}

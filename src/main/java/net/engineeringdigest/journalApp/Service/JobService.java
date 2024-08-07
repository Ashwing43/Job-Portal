package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.Job;
import net.engineeringdigest.journalApp.Entity.Recruiter;
import net.engineeringdigest.journalApp.Repository.JobRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
@Component
public class JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private RecruiterService recruiterService;

    public Job saveJob(@RequestBody Job job){
        return jobRepository.save(job);
    }

    @Transactional
    public void saveJobByRecruiterId(@RequestBody Job job, @RequestBody ObjectId id) throws Exception {
        try{
            Recruiter recruiter = recruiterService.getRecruiterById(id);
            job.setRecruiterId(recruiter);
            jobRepository.save(job);
            recruiter.getRequirementOfRecruiter().add(job);
            recruiterService.saveRecruiter(recruiter);
        }catch(Exception e){
            throw new Exception("Something went wrong", e);
        }
    }
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
}

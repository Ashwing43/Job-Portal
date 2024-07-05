package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.Job;
import net.engineeringdigest.journalApp.Repository.JobRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Objects;
@Component
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @PostMapping
    public void saveJob(@RequestBody Job job){
        jobRepository.save(job);
    }

    @GetMapping()
    public List<Job> getAllJobs(){
        return jobRepository.findAll();
    }
}

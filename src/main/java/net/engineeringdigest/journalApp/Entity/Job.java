package net.engineeringdigest.journalApp.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "jobs")
@Data
@NoArgsConstructor
public class Job {
    @Id
    private ObjectId id;
    private String position;
    private String Description;
    private boolean compensationInLpa;
    private LocalDate dateOfJoining;
    private LocalDate dateOfPosting;
    @DBRef
    private Recruiter recruiterId;
    @DBRef
    private List<Candidate> candidatesApplied = new ArrayList<>();
}

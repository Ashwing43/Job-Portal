package net.engineeringdigest.journalApp.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "jobs")
@Data
@NoArgsConstructor
public class Job {
    @Id
    private ObjectId id;
    private String position;
    private String Description;
    private double compensationInLpa;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfJoining;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfPosting;
    @DBRef
    private Recruiter recruiterId;
    @DBRef
    private List<Candidate> candidatesApplied = new ArrayList<>();
}

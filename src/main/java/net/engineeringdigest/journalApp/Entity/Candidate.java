package net.engineeringdigest.journalApp.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "candidates")
@Data
@NoArgsConstructor
public class Candidate {
    @Id
    private ObjectId id;
    @NonNull
    private String name;
    @NonNull
    private String mobile;
    @NonNull
    @Indexed(unique = true)
    private String email;
    @NonNull
    private String password;
    private double degreeCgpa;
    private double hscPercentage;
    private double sscPercentage;
    private String github;
    private String linkedIn;
    private List<String> skills = new ArrayList<>();
    @DBRef
    private List<Job> appliedRequirements = new ArrayList<>();
}

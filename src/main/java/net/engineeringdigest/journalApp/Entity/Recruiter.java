package net.engineeringdigest.journalApp.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "recruiter")
@Data
@NoArgsConstructor
public class Recruiter {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    private String username;
    private String mobile;
    private String email;
    private String password;
    private String companyName;
    @DBRef(lazy = true)
    @JsonManagedReference
    private List<Job> requirementOfRecruiter = new ArrayList<>();
    private List<String> Roles = new ArrayList<>();
}
package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.Entity.Recruiter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecruiterRepository extends MongoRepository<Recruiter, ObjectId> {
    Recruiter findRecruiterByUsername(String name);
//    Recruiter findRecruiterById(ObjectId id);
}

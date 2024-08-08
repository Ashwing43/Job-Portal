package net.engineeringdigest.jobPortal.Repository;

import net.engineeringdigest.jobPortal.Entity.Recruiter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecruiterRepository extends MongoRepository<Recruiter, ObjectId> {
    Recruiter findRecruiterByUsername(String name);
//    Recruiter findRecruiterById(ObjectId id);
}

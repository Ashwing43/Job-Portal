package net.engineeringdigest.jobPortal.Repository;

import net.engineeringdigest.jobPortal.Entity.Candidate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface CandidateRepository extends MongoRepository<Candidate, ObjectId> {
    Candidate findCandidateById(ObjectId id);
    Candidate findCandidateByUsername(String username);
}
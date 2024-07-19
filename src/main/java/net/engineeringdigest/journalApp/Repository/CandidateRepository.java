package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.Entity.Candidate;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CandidateRepository extends MongoRepository<Candidate, ObjectId> {
    Candidate findCandidateById(ObjectId id);
    Candidate findCandidateByUsername(String username);
}
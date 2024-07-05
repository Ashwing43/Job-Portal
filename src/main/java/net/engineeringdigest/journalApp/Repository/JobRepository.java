package net.engineeringdigest.journalApp.Repository;

import net.engineeringdigest.journalApp.Entity.Job;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job, ObjectId> {
}

package net.engineeringdigest.jobPortal.Repository;

import net.engineeringdigest.jobPortal.Entity.Job;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JobRepository extends MongoRepository<Job, ObjectId> {
}

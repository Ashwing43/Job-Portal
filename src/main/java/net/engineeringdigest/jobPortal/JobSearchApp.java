package net.engineeringdigest.jobPortal;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JobSearchApp {
    public static void main(String[] args) {
        SpringApplication.run(JobSearchApp.class, args);
    }
    public PlatformTransactionManager mongoTransactions(MongoDatabaseFactory dbFactory){
        return new MongoTransactionManager(dbFactory);
    }
}
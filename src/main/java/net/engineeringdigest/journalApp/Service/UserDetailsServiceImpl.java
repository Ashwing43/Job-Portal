package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.Candidate;
import net.engineeringdigest.journalApp.Entity.Recruiter;
import net.engineeringdigest.journalApp.Repository.CandidateRepository;
import net.engineeringdigest.journalApp.Repository.RecruiterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CandidateRepository candidateRepository;
    @Autowired
    private RecruiterRepository recruiterRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Candidate user = (Candidate) candidateRepository.findCandidateByUsername(username);
        Recruiter user2 = (Recruiter) recruiterRepository.findRecruiterByUsername(username);
        if(user!= null){
            return org.springframework.security.core
                    .userdetails.User.builder().username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))
                    .build();
        }
        if(user2 != null){
            return org.springframework.security.core
                    .userdetails.User.builder().username(user2.getUsername())
                    .password(user2.getPassword())
                    .roles(user2.getRoles().toArray(new String[0]))
                    .build();
        }
        throw new UsernameNotFoundException("Username not found");
    }
}

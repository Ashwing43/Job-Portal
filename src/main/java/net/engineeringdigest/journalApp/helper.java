package net.engineeringdigest.journalApp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helper {
    @GetMapping("/hello")
    public String help(){
        return "Hello Ashwin";
    }
}
package net.engineeringdigest.jobPortal.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health-check")
public class HealthCheck {
    @GetMapping
    public String healthCheck(){
        return "OK";
    }
}
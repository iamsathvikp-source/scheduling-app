package com.scheduling.scheduler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //this class handles web requests.
public class HealthController {

    @GetMapping("/health") //Spring knows to run it when someone visits /health.
    public String getHealth() {
        return "Scheduler is up and running";
    }
}

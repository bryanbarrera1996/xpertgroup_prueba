package com.devsecops;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class NumericController {

    private static final Logger logger = LoggerFactory.getLogger(NumericController.class);
    private static final String BASE_URL = "http://node-service:5000/plusone";

    private final RestTemplate restTemplate;

    public NumericController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String welcome() {
        return "Kubernetes DevSecOps";
    }

    @GetMapping("/compare/{value}")
    public String compareToFifty(@PathVariable int value) {
        if (value > 50) {
            return "Greater than 50";
        }
        return "Smaller than or equal to 50";
    }

    @GetMapping("/increment/{value}")
    public int increment(@PathVariable int value) {
        logger.info("Value Received in Request - {}", value);

        ResponseEntity<String> response = restTemplate.getForEntity(
                BASE_URL + "/" + value, String.class);

        logger.info("Node Service Response - {}", response.getBody());

        return Integer.parseInt(response.getBody());
    }
}

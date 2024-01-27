package com.levi.statsapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class MatchController {

    @GetMapping("/test")
    public String testEndPoint() {
        return "Actuator test";
    }
}

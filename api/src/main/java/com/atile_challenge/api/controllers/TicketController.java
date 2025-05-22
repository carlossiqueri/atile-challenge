package com.atile_challenge.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/tickets")
public class TicketController {
    
    @GetMapping
    public Map<String, String> getMethodName() {
        Map<String, String> health = new HashMap<>();
        health.put("message", "healty");
        return health;
    }
    
}

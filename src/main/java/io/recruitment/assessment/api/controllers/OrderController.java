package io.recruitment.assessment.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    Map<String, String> getAll() {
        return Map.of("message", "Hi there!");
    }

    @GetMapping("/{id}")
    Map<String, String> getOne(@PathVariable String id) {
        return Map.of("message", id);
    }
}


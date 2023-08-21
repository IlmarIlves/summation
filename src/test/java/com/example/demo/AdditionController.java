package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdditionController {

    @GetMapping("/add")
    public String addNumbers(
            @RequestParam(name = "num1") int num1,
            @RequestParam(name = "num2") int num2) {
        int sum = num1 + num2;
        return "The sum of " + num1 + " and " + num2 + " is: " + sum;
    }
}
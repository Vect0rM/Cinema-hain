package com.example.cinemahain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CinemasController {
    @GetMapping("/cinemas")
    String getCinemas(Model model) {
        return "cinemas";
    }
}

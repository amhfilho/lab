package com.example.demoh2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PropertyController {
    @Autowired
    private PropertyRepository repository;

    @GetMapping("/props")
    public String properties(Model model) {
        model.addAttribute("props",repository.findAll());
        return "properties";
    }
}

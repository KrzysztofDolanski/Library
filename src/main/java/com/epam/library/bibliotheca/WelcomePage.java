package com.epam.library.bibliotheca;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping({"", "/index"})
public class WelcomePage {


    @GetMapping
    String start() {
        return "index";
    }

}

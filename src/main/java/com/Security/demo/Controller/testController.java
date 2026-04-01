package com.Security.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class testController {

    @GetMapping("/publico")
    public String rotaPublica(){
        return "Area Publica";
    }

    @GetMapping("/privada")
    public String rotaPrivada(){
        return "Area Privada";
    }
}

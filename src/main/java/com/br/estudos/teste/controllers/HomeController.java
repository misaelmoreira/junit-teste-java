package com.br.estudos.teste.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.estudos.teste.modelViews.Home;

@RestController
public class HomeController {

    @GetMapping("/")
    public Home index(){
        return new Home();
    }    
}

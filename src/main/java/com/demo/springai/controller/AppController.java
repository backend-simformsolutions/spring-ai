package com.demo.springai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping("/index")
    public String generalAI(){
        return "index.html";
    }

    @GetMapping("/generate-recipe")
    public String recipeGenerator(){
        return "RecipeGenerator.html";
    }

    @GetMapping("/suggest-diet")
    public String suggestDiet(){
        return "SuggestDiet.html";
    }

    @GetMapping("/generate-image")
    public String generateImage(){
        return "GenerateImage.html";
    }
}

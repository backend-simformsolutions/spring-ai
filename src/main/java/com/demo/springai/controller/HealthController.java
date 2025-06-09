package com.demo.springai.controller;

import com.demo.springai.dto.DietRequestDetails;
import com.demo.springai.dto.RecipeRequestDto;
import com.demo.springai.service.RecipeService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HealthController {

    private RecipeService recipeService;

    public HealthController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/create-recipe")
    public String recipeCreator(@RequestBody RecipeRequestDto requestDto) {
        return recipeService.createRecipe(requestDto);
    }

    @PostMapping("/suggest-diet-recipe")
    public String suggestDiet(@RequestBody DietRequestDetails dietRequestDetails) {
        return recipeService.suggestDiet(dietRequestDetails);
    }
}

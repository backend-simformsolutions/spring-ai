package com.demo.springai.service;

import com.demo.springai.dto.DietRequestDetails;
import com.demo.springai.dto.RecipeRequestDto;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RecipeService {
    private final ChatModel chatModel;

    public RecipeService(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String createRecipe(RecipeRequestDto requestDto) {
        var template = """
                I want to create a recipe using the following ingredients: {ingredients}.
                The cuisine type I prefer is {cuisine}.
                Please consider the following dietary restrictions: {dietaryRestrictions}.
                Please provide me with a detailed recipe including title, list of ingredients, and cooking instructions
                """;

        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "ingredients",requestDto.getIngredient(),
                "cuisine", requestDto.getCuisine(),
                "dietaryRestrictions", requestDto.getDietaryRestriction()
        );

        Prompt prompt = promptTemplate.create(params);
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }

    public String suggestDiet(DietRequestDetails dietRequestDetails) {
        var template = """
                Based on the height of {height} cm, weight of {weight} kg, age of {age} years, gender {gender}, activity level {activityLevel}, 
                and dietary restrictions {dietaryRestrictions}, suggest a suitable diet plan.
                Please provide a detailed diet plan including breakfast, lunch, dinner, and snacks.
                """;

        Prompt prompt = getPrompt(dietRequestDetails, template);
        return chatModel.call(prompt).getResult().getOutput().getContent();
    }

    private static Prompt getPrompt(DietRequestDetails dietRequestDetails, String template) {
        PromptTemplate promptTemplate = new PromptTemplate(template);
        Map<String, Object> params = Map.of(
                "height", dietRequestDetails.getHeight(),
                "weight", dietRequestDetails.getWeight(),
                "dietGoal", dietRequestDetails.getDietGoal(),
                "age", dietRequestDetails.getAge(),
                "gender", dietRequestDetails.getGender(),
                "activityLevel", dietRequestDetails.getActivityLevel(),
                "dietaryRestrictions", dietRequestDetails.getDietaryRestrictions()
        );
        Prompt prompt = promptTemplate.create(params);
        return prompt;
    }
}
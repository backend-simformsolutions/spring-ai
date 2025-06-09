package com.demo.springai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRequestDto {

    String ingredient;
    String cuisine;
    String dietaryRestriction;
}

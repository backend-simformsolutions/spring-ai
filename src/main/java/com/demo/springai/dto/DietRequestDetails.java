package com.demo.springai.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DietRequestDetails {

    private double height;
    private double weight;
    private String dietGoal;
    private int age;
    private String gender;
    private String activityLevel;
    private String dietaryRestrictions;
}

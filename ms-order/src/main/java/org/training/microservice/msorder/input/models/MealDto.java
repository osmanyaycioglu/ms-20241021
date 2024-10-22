package org.training.microservice.msorder.input.models;

import lombok.Data;

@Data
public class MealDto {
    private String mealName;
    private Double portion;
}

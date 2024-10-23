package org.training.microservice.msorder.input.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.training.microservice.msorder.input.validation.CheckWords;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OrderQueryDto {
    private String customerName;
    private String customerSurname;
    private ZonedDateTime scheduleTime;
    private List<MealDto> meals;
}

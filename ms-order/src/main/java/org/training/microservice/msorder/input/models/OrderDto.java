package org.training.microservice.msorder.input.models;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.training.microservice.msorder.input.validation.CheckWords;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OrderDto {
    @NotBlank
    @CheckWords({"abc","xyz","123"})
    private String customerName;
    @NotEmpty
    @CheckWords({"abc","xyz","123"})
    private String customerSurname;
    @NotBlank
    @Size(min = 10,max = 12)
    private String customerPhone;
    // @Future
    // @NotNull
    private ZonedDateTime scheduleTime;
    @Size(min = 1,max = 20)
    private List<MealDto> meals;
}

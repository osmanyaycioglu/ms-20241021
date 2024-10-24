package org.training.microservice.msorder.services.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.training.microservice.msorder.input.models.MealDto;
import org.training.microservice.msorder.input.validation.CheckWords;

import java.time.ZonedDateTime;
import java.util.List;

@Data
public class Order {
    private String         orderId;
    private String         customerId;
    private ZonedDateTime  scheduleTime;
    private List<Meal>     meals;
    private EOrderStatus   orderStatus;
    private String         kitchenReservationId;
    private List<OrderLog> orderLogs;
}

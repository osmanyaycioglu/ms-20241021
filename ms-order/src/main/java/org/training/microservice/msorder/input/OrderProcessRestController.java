package org.training.microservice.msorder.input;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.microservice.msorder.input.models.OrderDto;
import org.training.microservice.msorder.input.models.OrderResponse;
import org.training.microservice.msorder.services.OrderProcessService;

import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order/process")
@RequiredArgsConstructor
public class OrderProcessRestController {
    private final OrderProcessService orderProcessService;



    @Operation(summary = "order place için kullnılıyor",description = "daha uzun order place için kullnılıyor")
    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderDto orderDtoParam) {
        orderProcessService.place();
        return ResponseEntity.status(HttpStatus.CREATED)
                             .header("orderStatus",
                                     "created")
                             .body(OrderResponse.builder()
                                                .withDesc("alındı")
                                                .withOrderId(UUID.randomUUID()
                                                                 .toString())
                                                .withScheduleTime(ZonedDateTime.now()
                                                                               .plusHours(1))
                                                .build());
    }

    @GetMapping("/cancel/{orderId}")
    public String cancelOrder(@PathVariable("orderId") String orderId) {
        return "OK";
    }

    @GetMapping("/schedule")
    public String scheduleOrder(@RequestParam("orderId") String orderId,
                              @RequestParam("st") ZonedDateTime st) {
        return "OK";
    }

    @GetMapping("/pause/{orderId}")
    public String pauseOrder(@PathVariable("orderId") String orderId) {
        return "OK";
    }

}

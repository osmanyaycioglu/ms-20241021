package org.training.microservice.msorder.input;


import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.training.microservice.msorder.input.mappers.IOrderMapper;
import org.training.microservice.msorder.input.models.OrderDto;
import org.training.microservice.msorder.input.models.OrderResponse;
import org.training.microservice.msorder.services.OrderProcessService;
import org.training.microservice.msorder.services.models.OrderCreationResult;

import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order/process")
@RequiredArgsConstructor
public class OrderProcessRestController {
    private final OrderProcessService orderProcessService;

    @Value("${server.port}")
    private int port;

    @Operation(summary = "order place için kullnılıyor", description = "daha uzun order place için kullnılıyor")
    @PostMapping("/place")
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderDto orderDtoParam) {
        OrderCreationResult orderCreationResultLoc = orderProcessService.place(IOrderMapper.ORDER_MAPPER.toOrder(orderDtoParam));
        return ResponseEntity.status(HttpStatus.CREATED)
                             .header("orderStatus",
                                     "created")
                             .body(OrderResponse.builder()
                                                .withDesc(orderCreationResultLoc.getDesc())
                                                .withOrderId(orderCreationResultLoc.getOrderId())
                                                .withScheduleTime(ZonedDateTime.now()
                                                                               .plusHours(1))
                                                .build());
    }

    @PostMapping("/place2")
    public ResponseEntity<OrderResponse> placeOrder2(@Valid @RequestBody OrderDto orderDtoParam) {
        OrderCreationResult orderCreationResultLoc = orderProcessService.place2(IOrderMapper.ORDER_MAPPER.toOrder(orderDtoParam));
        return ResponseEntity.status(HttpStatus.CREATED)
                             .header("orderStatus",
                                     "created")
                             .body(OrderResponse.builder()
                                                .withDesc(orderCreationResultLoc.getDesc())
                                                .withOrderId(orderCreationResultLoc.getOrderId())
                                                .withScheduleTime(ZonedDateTime.now()
                                                                               .plusHours(1))
                                                .build());
    }

    @PostMapping("/place3")
    public ResponseEntity<OrderResponse> placeOrder3(@Valid @RequestBody OrderDto orderDtoParam) {
        OrderCreationResult orderCreationResultLoc = orderProcessService.place3(IOrderMapper.ORDER_MAPPER.toOrder(orderDtoParam));
        return ResponseEntity.status(HttpStatus.CREATED)
                             .header("orderStatus",
                                     "created")
                             .body(OrderResponse.builder()
                                                .withDesc(orderCreationResultLoc.getDesc() + " order port : " + port)
                                                .withOrderId(orderCreationResultLoc.getOrderId())
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

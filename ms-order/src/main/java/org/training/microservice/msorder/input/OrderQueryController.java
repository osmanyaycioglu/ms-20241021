package org.training.microservice.msorder.input;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.training.microservice.msorder.input.models.OrderDto;
import org.training.microservice.msorder.input.models.OrderQueryDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order/query")
@RefreshScope
public class OrderQueryController {

    @GetMapping("/get/one/order")
    public OrderQueryDto getOrder(@RequestParam String orderId){
        return null;
    }

    @GetMapping("/get/active/orders")
    public List<OrderQueryDto> getActiveOrders(){
        return null;
    }

}

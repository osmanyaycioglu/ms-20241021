package org.training.microservice.msorder.input;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.training.microservice.msorder.input.models.OrderDto;
import org.training.microservice.msorder.input.models.OrderResponse;
import org.training.microservice.msorder.input.models.Response;

@RestController
@RequestMapping("/order")
public class OrderRestController {

    public Response<OrderResponse> insertOrder(@RequestBody OrderDto orderDtoParam) {
        return null;
    }

    public void deleteOrder() {
    }

    public void queryOrder() {
    }

}

package org.training.microservice.msorder.input;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.training.microservice.mscommon.error.ErrorObj;
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

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(IllegalArgumentException exp) {
        return ErrorObj.builder()
                       .withDescParam(exp.getMessage())
                       .build();
    }


}

package org.training.microservice.msorder.services.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(setterPrefix = "with")
public class OrderCreationResult {
    private String customerId;
    private String orderId;
    private String transId;
    private String desc;
}

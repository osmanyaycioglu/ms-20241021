package org.training.microservice.msaccounting;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentRequest {
    private String     orderId;
    private BigDecimal amount;
    private String     customerId;
}

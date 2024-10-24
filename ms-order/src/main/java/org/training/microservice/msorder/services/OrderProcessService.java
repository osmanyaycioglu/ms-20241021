package org.training.microservice.msorder.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.training.microservice.msorder.integration.AccountingIntegration;
import org.training.microservice.msorder.integration.models.PaymentResponse;
import org.training.microservice.msorder.services.models.Order;
import org.training.microservice.msorder.services.models.OrderCreationResult;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderProcessService {
    private final AccountingIntegration accountingIntegration;

    public OrderCreationResult place(Order orderParam) {
        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        String customerId = UUID.randomUUID()
                                .toString();
        PaymentResponse paymentResponseLoc = accountingIntegration.pay(orderParam.getOrderId(),
                                                                       new BigDecimal(1000),
                                                                       customerId);

        return OrderCreationResult.builder()
                                  .withOrderId(orderParam.getOrderId())
                                  .withCustomerId(customerId)
                                  .withTransId(paymentResponseLoc.getTransId())
                                  .withDesc(paymentResponseLoc.getDesc())
                                  .build();
    }

    public OrderCreationResult place2(Order orderParam) {
        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        String customerId = UUID.randomUUID()
                                .toString();
        PaymentResponse paymentResponseLoc = accountingIntegration.pay2(orderParam.getOrderId(),
                                                                        new BigDecimal(1000),
                                                                        customerId);

        return OrderCreationResult.builder()
                                  .withOrderId(orderParam.getOrderId())
                                  .withCustomerId(customerId)
                                  .withTransId(paymentResponseLoc.getTransId())
                                  .withDesc(paymentResponseLoc.getDesc())
                                  .build();
    }

    public OrderCreationResult place3(Order orderParam) {
        orderParam.setOrderId(UUID.randomUUID()
                                  .toString());
        String customerId = UUID.randomUUID()
                                .toString();
        PaymentResponse paymentResponseLoc = accountingIntegration.pay3(orderParam.getOrderId(),
                                                                        new BigDecimal(1000),
                                                                        customerId);

        return OrderCreationResult.builder()
                                  .withOrderId(orderParam.getOrderId())
                                  .withCustomerId(customerId)
                                  .withTransId(paymentResponseLoc.getTransId())
                                  .withDesc(paymentResponseLoc.getDesc())
                                  .build();
    }

}

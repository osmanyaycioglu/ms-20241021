package org.training.microservice.msorder.integration.resiliecy;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;
import org.training.microservice.msorder.integration.models.PaymentResponse;

import java.math.BigDecimal;
import java.util.UUID;

@Component
public class Callee {
    private long counter = -1;


    @Retry(name = "accounting-pay3-retry", fallbackMethod = "pay3Fallback")
    @CircuitBreaker(name = "accounting-pay3-cb")
    public PaymentResponse pay3(String orderId,
                                BigDecimal amount,
                                String customerId) {
        counter++;
        System.out.println("------ Called orderId : " + orderId + " counter : " + counter);

        if (counter < 11 && counter % 3 == 0) {
            throw new IllegalStateException("error");
        } else if (counter % 6 == 0) {
            throw new IllegalStateException("error");
        }
        return new PaymentResponse(UUID.randomUUID()
                                       .toString(),
                                   orderId,
                                   "test");
    }

    public PaymentResponse pay3Fallback(String orderId,
                                        BigDecimal amount,
                                        String customerId,
                                        Throwable throwableParam) {
        System.out.println("pay3 ------ Fallback ");
        return new PaymentResponse(UUID.randomUUID()
                                       .toString(),
                                   orderId,
                                   "test fallback");
    }


}

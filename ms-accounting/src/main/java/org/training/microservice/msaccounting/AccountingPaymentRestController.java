package org.training.microservice.msaccounting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounting/payment")
public class AccountingPaymentRestController {
    @Value("${server.port}")
    private int port;

    @PostMapping("/pay")
    public PaymentResponse pay(@RequestBody PaymentRequest paymentRequestParam) {
        return new PaymentResponse(UUID.randomUUID()
                                       .toString(),
                                   paymentRequestParam.getOrderId(),
                                   "Ödeme yapıldı : " + port);
    }

}

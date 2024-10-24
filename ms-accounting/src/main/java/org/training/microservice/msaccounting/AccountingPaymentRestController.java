package org.training.microservice.msaccounting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/v1/accounting/payment")
public class AccountingPaymentRestController {
    @Value("${server.port}")
    private int        port;
    private AtomicLong atomicLong = new AtomicLong();

    @PostMapping("/pay")
    public PaymentResponse pay(@RequestBody PaymentRequest paymentRequestParam) {
        long lLoc = atomicLong.incrementAndGet();
        if (lLoc % 3 == 0) {
            throw new IllegalStateException("deneme erroru");
        }
        return new PaymentResponse(UUID.randomUUID()
                                       .toString(),
                                   paymentRequestParam.getOrderId(),
                                   "Ödeme yapıldı : " + port);
    }

}

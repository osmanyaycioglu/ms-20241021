package org.training.microservice.msorder.integration;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.training.microservice.msorder.integration.models.PaymentRequest;
import org.training.microservice.msorder.integration.models.PaymentResponse;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class AccountingIntegration {
    private final RestTemplate           restTemplate;
    private final EurekaClient           eurekaClient;
    private final IAccountingFeignClient accountingFeignClient;
    private       AtomicInteger          counter = new AtomicInteger();

    public PaymentResponse pay(String orderId,
                               BigDecimal amount,
                               String customerId) {
        PaymentRequest paymentRequestLoc = new PaymentRequest(orderId,
                                                              amount,
                                                              customerId);
        PaymentResponse paymentResponseLoc = restTemplate.postForObject("http://ACCOUNTING/api/v1/accounting/payment/pay",
                                                                        paymentRequestLoc,
                                                                        PaymentResponse.class);
        return paymentResponseLoc;
    }

    public PaymentResponse pay2(String orderId,
                                BigDecimal amount,
                                String customerId) {
        int iLoc = counter.incrementAndGet();

        PaymentRequest paymentRequestLoc = new PaymentRequest(orderId,
                                                              amount,
                                                              customerId);
        Application        applicationLoc  = eurekaClient.getApplication("ACCOUNTING");
        List<InstanceInfo> instancesLoc    = applicationLoc.getInstances();
        RestTemplate       restTemplateLoc = new RestTemplate();
        int                index           = iLoc % instancesLoc.size();
        System.out.println("Counter : "
                           + iLoc
                           + "Eureka client index : "
                           + index
                           + " Eureka client list size : "
                           + instancesLoc.size());
        InstanceInfo instanceInfoLoc = instancesLoc.get(index);
        PaymentResponse paymentResponseLoc = restTemplateLoc.postForObject("http://"
                                                                           + instanceInfoLoc.getIPAddr()
                                                                           + ":"
                                                                           + instanceInfoLoc.getPort()
                                                                           + "/api/v1/accounting/payment/pay",
                                                                           paymentRequestLoc,
                                                                           PaymentResponse.class);
        return paymentResponseLoc;
    }

    @Retry(name = "accounting-pay3-retry",fallbackMethod = "pay3Fallback")
    public PaymentResponse pay3(String orderId,
                                BigDecimal amount,
                                String customerId) {
        PaymentRequest paymentRequestLoc = new PaymentRequest(orderId,
                                                              amount,
                                                              customerId);
        return accountingFeignClient.pay(paymentRequestLoc);
    }

    public PaymentResponse pay3Fallback(String orderId,
                                BigDecimal amount,
                                String customerId,Throwable throwableParam) {
        System.out.println("pay3 ------ Fallback ");
        return new PaymentResponse();
    }

}

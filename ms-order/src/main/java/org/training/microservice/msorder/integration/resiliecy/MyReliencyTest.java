package org.training.microservice.msorder.integration.resiliecy;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

// @Component
@RequiredArgsConstructor
public class MyReliencyTest implements CommandLineRunner {
    private final Callee                 callee;
    private final RetryRegistry          retryRegistry;
    private final CircuitBreakerRegistry circuitBreakerRegistry;

    @Override
    public void run(final String... args) throws Exception {
        Retry retryLoc = retryRegistry.retry("accounting-pay3-retry");
        retryLoc.getEventPublisher()
                .onSuccess(re -> System.out.println("Success Call event from retry"))
                .onRetry(re -> System.out.println("Retry event from retry"));
        CircuitBreaker circuitBreakerLoc = circuitBreakerRegistry.circuitBreaker("accounting-pay3-cb");
        for (int i = 0; i < 30; i++) {
            try {
                Thread.sleep(500);
            } catch (Exception exp) {
            }
            try {
                callee.pay3("order-" + i,
                            BigDecimal.TEN,
                            "customer-" + i);
            } catch (Exception exp) {
                System.out.println("Exception : " + exp.getClass().getName());
            }

            Retry.Metrics metricsLoc = retryLoc.getMetrics();
            System.out.println("******** Retry  s : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithoutRetryAttempt()
                               + " swr : "
                               + metricsLoc.getNumberOfSuccessfulCallsWithRetryAttempt()
                               + " f : "
                               + metricsLoc.getNumberOfFailedCallsWithoutRetryAttempt()
                               + " fwr : "
                               + metricsLoc.getNumberOfFailedCallsWithRetryAttempt());
            CircuitBreaker.Metrics cbMetricsLoc = circuitBreakerLoc.getMetrics();
            System.out.println("cb state : "
                               + circuitBreakerLoc.getState()
                               + " fr : "
                               + cbMetricsLoc.getFailureRate()
                               + " s : "
                               + cbMetricsLoc.getNumberOfSuccessfulCalls()
                               + " f : "
                               + cbMetricsLoc.getNumberOfFailedCalls()
                               + " np : "
                               + cbMetricsLoc.getNumberOfNotPermittedCalls()
                               + " scr: "
                               + cbMetricsLoc.getSlowCallRate()
                               + " sc : "
                               + cbMetricsLoc.getNumberOfSlowCalls()
                               + " sfc: "
                               + cbMetricsLoc.getNumberOfSlowFailedCalls()
                               + " bc : "
                               + cbMetricsLoc.getNumberOfBufferedCalls()
            );
        }
    }
}

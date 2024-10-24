package org.training.microservice.msorder.integration.resiliecy;

import org.training.microservice.msorder.integration.models.PaymentResponse;

import java.util.function.Predicate;

public class MyResultPredicate implements Predicate<PaymentResponse> {
    @Override
    public boolean test(final PaymentResponse paymentResponseParam) {
        System.out.println("+^+%^+%^+% ----- Result Predicate : " + paymentResponseParam);
        if (paymentResponseParam.getTransId() == null){
            return true;
        }
        return false;
    }
}

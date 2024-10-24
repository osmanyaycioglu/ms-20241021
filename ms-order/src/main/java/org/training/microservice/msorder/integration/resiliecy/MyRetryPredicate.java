package org.training.microservice.msorder.integration.resiliecy;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestClientResponseException;
import org.training.microservice.mscommon.error.ErrorObj;
import org.training.microservice.mscommon.error.RemoteCallException;

import java.io.IOException;
import java.util.function.Predicate;

public class MyRetryPredicate implements Predicate<Throwable> {

    @Override
    public boolean test(final Throwable throwableParam) {
        System.out.println("'^+^&+%&+%& -------- Retry exp : " + throwableParam);
        if (throwableParam instanceof RestClientResponseException) {
            RestClientResponseException exceptionLoc    = (RestClientResponseException) throwableParam;
            ObjectMapper                objectMapperLoc = new ObjectMapper();
            try {
                ErrorObj errorObjLoc = objectMapperLoc.readValue(exceptionLoc.getResponseBodyAsByteArray(),
                                                                 ErrorObj.class);
                return switch (errorObjLoc.getCode()) {
                    case 1024, 1026, 2048, 2055 -> true;
                    default -> false;
                };
            } catch (IOException eParam) {
                return false;
            }
        } else if (throwableParam instanceof RemoteCallException) {
            RemoteCallException exceptionLoc = (RemoteCallException) throwableParam;
            ErrorObj            errorObjLoc  = exceptionLoc.getErrorObj();
            return switch (errorObjLoc.getCode()) {
                case 1024, 1026, 2048, 2055 -> true;
                default -> false;
            };
        }
        return true;
    }
}

package org.training.microservice.mscommon.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class MyErrorDecoder implements ErrorDecoder {
    @Override
    public RemoteCallException decode(final String sParam,
                            final Response responseParam) {
        ObjectMapper objectMapperLoc = new ObjectMapper();
        try {
            Response.Body bodyLoc = responseParam.body();
            System.out.println("Error body  : " + bodyLoc.toString());

            ErrorObj errorObjLoc = objectMapperLoc.readValue(bodyLoc
                                                                          .asInputStream(),
                                                             ErrorObj.class);
            return new RemoteCallException(errorObjLoc);
        } catch (IOException eParam) {
            eParam.printStackTrace();
            throw new RemoteCallException();
        }
    }
}

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
            ErrorObj errorObjLoc = objectMapperLoc.readValue(responseParam.body()
                                                                          .asInputStream(),
                                                             ErrorObj.class);
            return new RemoteCallException(errorObjLoc);
        } catch (IOException eParam) {
            throw new RemoteCallException();
        }
    }
}

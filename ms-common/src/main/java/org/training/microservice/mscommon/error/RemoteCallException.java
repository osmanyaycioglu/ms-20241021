package org.training.microservice.mscommon.error;

import lombok.Data;

@Data
public class RemoteCallException extends RuntimeException {
    private ErrorObj errorObj;

    public RemoteCallException() {
    }

    public RemoteCallException(final ErrorObj errorObjParam) {
        errorObj = errorObjParam;
    }
}

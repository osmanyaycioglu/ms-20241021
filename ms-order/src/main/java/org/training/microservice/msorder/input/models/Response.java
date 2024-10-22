package org.training.microservice.msorder.input.models;

import lombok.Data;

@Data
public class Response<T> {
    private boolean errorOccurred;
    private String  errorDesc;
    private Integer errorCode;
    private T       response;
}

package org.training.microservice.mscommon.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ErrorAdvice.class);

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(IllegalArgumentException exp) {
        return ErrorObj.builder()
                       .withDescParam(exp.getMessage())
                       .withCodeParam(1022)
                       .build();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(IllegalStateException exp) {
        return ErrorObj.builder()
                       .withDescParam(exp.getMessage())
                       .withCodeParam(2055)
                       .build();
    }

    @ExceptionHandler(RemoteCallException.class)
    public ResponseEntity<ErrorObj> handleException(RemoteCallException exp) {
        ErrorObj errorObjLoc = exp.getErrorObj();
        if (errorObjLoc.getCode() == 2055) {
            return ResponseEntity.status(HttpStatus.ALREADY_REPORTED)
                                 .body(ErrorObj.builder()
                                               .withDescParam(exp.getMessage())
                                               .withCodeParam(2055)
                                               .build());

        } else if (errorObjLoc.getCode() == 1024 || errorObjLoc.getCode() == 1026) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body(ErrorObj.builder()
                                               .withDescParam(exp.getMessage())
                                               .withCodeParam(2055)
                                               .build());

        }
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handleException(MethodArgumentNotValidException exp) {
        return ErrorObj.builder()
                       .withDescParam("Validation error")
                       .withCodeParam(1024)
                       .withSubErrorsParam(exp.getAllErrors()
                                              .stream()
                                              .<FieldError>map(oe -> (FieldError) oe)
                                              .map(fe -> ErrorObj.builder()
                                                                 .withDescParam("Tag :"
                                                                                + fe.getField()
                                                                                + " rejeceted : "
                                                                                + fe.getRejectedValue())
                                                                 .withCodeParam(1025)
                                                                 .build())
                                              .collect(Collectors.toList()))
                       .build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorObj> handleException(Exception exp) {
        if (logger.isInfoEnabled()) {
            logger.info("[ErrorAdvice][handleException]-> Exception : " + exp.getMessage(),
                        exp);
        }
        if (exp instanceof NullPointerException) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED)
                                 .body(ErrorObj.builder()
                                               .withDescParam(exp.getMessage())
                                               .withCodeParam(5001)
                                               .build());

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ErrorObj.builder()
                                           .withDescParam(exp.getMessage())
                                           .withCodeParam(5000)
                                           .build());
    }

}

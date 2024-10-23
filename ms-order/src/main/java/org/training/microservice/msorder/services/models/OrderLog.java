package org.training.microservice.msorder.services.models;

import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class OrderLog {
    private ZonedDateTime logDate;
    private String        log;
}

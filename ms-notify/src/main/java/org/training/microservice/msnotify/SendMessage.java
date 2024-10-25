package org.training.microservice.msnotify;

import lombok.Data;

@Data
public class SendMessage {
    private String msg;
    private String dest;
}

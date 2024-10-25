package org.training.microservice.msorder.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.training.microservice.msorder.integration.models.SendMessage;

@Service
@RequiredArgsConstructor
public class NotifyIntegration {
    private final RabbitTemplate rabbitTemplate;

    public void sendNotify(String msg,
                           String dest) {
        SendMessage sendMessageLoc = new SendMessage();
        sendMessageLoc.setMsg(msg);
        sendMessageLoc.setDest(dest);

        rabbitTemplate.convertAndSend("topic-exchange",
                                      "eu.west.turkey.msg.sms.ntf.p5",
                                      sendMessageLoc);
    }

}

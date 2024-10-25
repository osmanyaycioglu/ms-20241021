package org.training.microservice.msnotify;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MyRabbitListener {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "sms-notify-q", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "notify-exchange", durable = "true", autoDelete = "false", type = ExchangeTypes.DIRECT),
            key = "sms-notify"
    )
    )
    public void listenSMS(String stringParam){
        System.out.println("I got SMS : " + stringParam);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "email-notify-q", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "notify-exchange", durable = "true", autoDelete = "false", type = ExchangeTypes.DIRECT),
            key = "sms-notify"
    )
    )
    public void listenEmail(String stringParam){
        System.out.println("I got Email : " + stringParam);
    }

}

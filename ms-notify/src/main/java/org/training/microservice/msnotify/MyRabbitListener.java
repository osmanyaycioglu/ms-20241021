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
    public void listenSMS(SendMessage stringParam){
        System.out.println("I got SMS : " + stringParam);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "email-notify-q", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "notify-exchange", durable = "true", autoDelete = "false", type = ExchangeTypes.DIRECT),
            key = "email-notify"
    )
    )
    public void listenEmail(SendMessage stringParam){
        System.out.println("I got Email : " + stringParam);
    }

    // eu.north.germany.msg.sms.adv.p1
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "sms-topic-q", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "topic-exchange", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
            key = "*.*.*.msg.sms.#"
    )
    )
    public void listenTopicMsgSMS(SendMessage stringParam){
        System.out.println("I got topic SMS : " + stringParam);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "all-eu-sms-topic-q", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "topic-exchange", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
            key = "eu.*.*.msg.sms.#"
    )
    )
    public void listenTopicMsgEUSMS(SendMessage stringParam){
        System.out.println("I got Europe SMS : " + stringParam);
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "all-sms-topic-q", durable = "true", autoDelete = "false"),
            exchange = @Exchange(value = "topic-exchange", durable = "true", autoDelete = "false", type = ExchangeTypes.TOPIC),
            key = "#"
    )
    )
    public void listenTopicALL(SendMessage stringParam){
        System.out.println("I got ALL MESSAGES : " + stringParam);
    }

}

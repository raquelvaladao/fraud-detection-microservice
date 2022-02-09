package com.frdetection.notification.rabbitmq;

import com.frdetection.clients.notification.RequestNotification;
import com.frdetection.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consume(RequestNotification notificationRequest){
        log.info("Consumed {} from queue", notificationRequest.toString());
        notificationService.send(notificationRequest);


    }
}

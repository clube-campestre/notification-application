package com.campestre.clube.notification_application.consumer;


import com.campestre.clube.notification_application.dto.EmailMessageDto;
import com.campestre.clube.notification_application.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.campestre.clube.notification_application.utils.MessageExtensions.*;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${rabbitmq.queuename}")
    public void consume(EmailMessageDto message) {
        System.out.println(QUEUE_RECEIVED_MESSAGE.formatted(message.getEmail(), message.getCode()));
        emailService.sendEmail(message.getEmail(), SUBJECT_RESET_PASSWORD_EMAIL, message.getCode());
    }
}

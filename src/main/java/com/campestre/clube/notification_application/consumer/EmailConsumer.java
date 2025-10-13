package com.campestre.clube.notification_application.consumer;


import com.campestre.clube.notification_application.dto.ResetPasswordEmailDto;
import com.campestre.clube.notification_application.service.EmailService;
import com.campestre.clube.notification_application.utils.EmailTemplates;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.campestre.clube.notification_application.enums.NotificationTypeEnum.RESET_PASSWORD_EMAIL;
import static com.campestre.clube.notification_application.utils.MessageExtensions.*;

@Component
public class EmailConsumer {

    private final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${rabbitmq.queuename.resetpassword}")
    public void consumeResetPassword(ResetPasswordEmailDto message) {
        System.out.println(
                QUEUE_RECEIVED_MESSAGE.formatted(RESET_PASSWORD_EMAIL.name(), message.getEmail(), message.getCode())
        );
        emailService.sendEmail(
                message.getNotificationType(),
                message.getEmail(),
                EmailTemplates.htmlEmailResetPasswordTemplate(message.getCode())
        );
    }
}

package com.campestre.clube.notification_application.consumer;


import com.campestre.clube.notification_application.dto.EmailMessageDto;
import com.campestre.clube.notification_application.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    private final EmailService emailService;


    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${rabbitmq.queuename}")
    public void consume(EmailMessageDto message) {
        System.out.println("📥 Recebido da fila: " + message.getEmail() + " | " + message.getCode());
        emailService.sendEmail(
                message.getEmail(),
                "Código de recuperação de senha",
                 message.getCode()
        );
    }
}

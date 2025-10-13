package com.campestre.clube.notification_application.service;

import com.campestre.clube.notification_application.utils.EmailTemplates;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import static com.campestre.clube.notification_application.utils.MessageExtensions.*;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String code) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(EmailTemplates.htmlEmailResetPasswordTemplate(code), true);

            mailSender.send(mimeMessage);
            System.out.println(QUEUE_RECEIVED_RESET_PASSWORD_MESSAGE.formatted(to, code));
        } catch (MessagingException e) {
            throw new RuntimeException(INTERNAL_ERROR_SEND_EMAIL_MESSAGE);
        }
    }
}

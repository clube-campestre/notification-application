package com.campestre.clube.notification_application.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

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

// Corpo HTML
            String htmlContent = """
                <table align="center" border="0" cellpadding="0" cellspacing="0" width="100%%" 
                       style="max-width:600px; background-color:#ffffff; border-radius:8px; overflow:hidden; font-family: Arial, Helvetica, sans-serif;">
                    
                    <!-- Header -->
                    <tr>
                        <td align="center" bgcolor="#0a3d62" style="padding:20px; color:#ffffff;">
                            <table width="100%%" cellpadding="0" cellspacing="0">
                                <tr>
                                    <!-- Espaço para logo -->
                                    <td align="left" style="width:60px;">
                                        <img src="https://sg.sdasystems.org/cms/logos_clubes/14367.jpg" alt="Logo Clube Campestre" width="60" height="60" 
                                             style="border-radius:50%%; display:block;">
                                    </td>
                                    <td align="center" style="color:#ffffff; font-size:24px; font-weight:bold;">
                                        Clube Campestre
                                    </td>
                                    <td style="width:60px;"></td> <!-- Espaço de equilíbrio -->
                                </tr>
                            </table>
                        </td>
                    </tr>
            
                    <!-- Corpo -->
                    <tr>
                        <td style="padding:30px; color:#333333; font-size:16px; line-height:1.5;">
                            <p>Olá,</p>
                            <p>Você solicitou a recuperação de senha. Aqui está seu código:</p>
                            <div style="font-size:20px; font-weight:bold; margin:20px 0; color:#E74C3C; text-align:center;">
                                %s
                            </div>
                            <p>O código expira em <b>10 minutos</b>.</p>
                            <br>
                            <p style="font-size:12px; color:#888;">Se você não solicitou, ignore este e-mail.</p>
                        </td>
                    </tr>
            
                    <!-- Rodapé -->
                    <tr>
                        <td align="center" bgcolor="#8B0000" style="padding:15px; color:#ffffff; font-size:13px;">
                            © 2025 Clube Campestre - Todos os direitos reservados.
                        </td>
                    </tr>
                </table>
            """.formatted(code);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true); // true = HTML

            mailSender.send(mimeMessage);

            System.out.println("✅ Email enviado para: " + to + " | codigo:" + code);
        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar email", e);
        }
    }
}

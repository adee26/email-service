package com.adelinasportfolio.emailservice.service;

import com.adelinasportfolio.emailservice.exception.EmailServiceException;
import com.adelinasportfolio.emailservice.model.EmailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.adelinasportfolio.emailservice.constants.EmailConstants.EMAIL_SENT_SUCCESSFULLY;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String FROM_EMAIL;

    @Override
    public String sendSimpleMessage(EmailEntity emailEntity) {
        final var message = new SimpleMailMessage();
        message.setFrom(FROM_EMAIL);
        message.setTo(emailEntity.getTo());
        message.setSubject(emailEntity.getSubject());
        message.setText(emailEntity.getText());

        try {
            javaMailSender.send(message);
            return EMAIL_SENT_SUCCESSFULLY;
        } catch (MailException e) {
           throw new EmailServiceException(e.getMessage());
        }
    }
}

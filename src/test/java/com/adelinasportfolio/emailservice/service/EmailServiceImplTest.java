package com.adelinasportfolio.emailservice.service;

import com.adelinasportfolio.emailservice.exception.EmailServiceException;
import com.adelinasportfolio.emailservice.model.EmailEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static com.adelinasportfolio.emailservice.util.TestConstants.EMAIL_SENT_SUCCESSFULLY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EmailServiceImplTest {
    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private EmailServiceImpl emailService;

    @Test
    void shouldSendSimpleMessage() {
        final var message = buildSimpleMailMessage();

        doNothing().when(javaMailSender).send(message);

        assertEquals(EMAIL_SENT_SUCCESSFULLY, emailService.sendSimpleMessage(buildEmailEntity()));
        verify(javaMailSender).send(message);
    }

    @Test
    void shouldHandleMailException() {
        final var message = buildSimpleMailMessage();
        doThrow(EmailServiceException.class).when(javaMailSender).send(message);

        assertThrows(EmailServiceException.class, () -> emailService.sendSimpleMessage(buildEmailEntity()));

        verify(javaMailSender).send(message);

    }

    private SimpleMailMessage buildSimpleMailMessage() {
        final var message = new SimpleMailMessage();
        message.setTo("test@test.com");
        message.setText("Test");
        message.setSubject("Test");

        return message;
    }

    private EmailEntity buildEmailEntity() {
        return EmailEntity.builder()
                .to("test@test.com")
                .text("Test")
                .subject("Test")
                .build();
    }

}
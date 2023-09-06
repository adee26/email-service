package com.adelinasportfolio.emailservice.service;

import com.adelinasportfolio.emailservice.model.EmailEntity;

public interface EmailService {
    String sendSimpleMessage(EmailEntity emailEntity);
}

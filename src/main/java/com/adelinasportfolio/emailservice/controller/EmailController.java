package com.adelinasportfolio.emailservice.controller;

import com.adelinasportfolio.emailservice.model.EmailEntity;
import com.adelinasportfolio.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/sendEmail")
public class EmailController {
    private final EmailService emailService;
    @PostMapping
    public ResponseEntity<String> sendEmail(@RequestBody EmailEntity emailEntity) {
        return ResponseEntity.ok(emailService.sendSimpleMessage(emailEntity));
    }
}

package com.example.springawsses.service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${sender}")
    private String sender;

    private final AmazonSimpleEmailService amazonSimpleEmailService;

    public void send(String subject, String content, List<String> receivers) {
        Destination destination = new Destination().withToAddresses(receivers);

        Content subjectContent = createContent(subject);
        Content mailContent = createContent(content);

        Message message = new Message()
                .withSubject(subjectContent)
                .withBody(new Body().withHtml(mailContent));

        SendEmailRequest sendEmailRequest = new SendEmailRequest()
                .withSource(sender)
                .withDestination(destination)
                .withMessage(message);

        amazonSimpleEmailService.sendEmail(sendEmailRequest);
    }

    public Content createContent(String text) {
        return new Content().withCharset("UTF_8").withData(text);
    }
}

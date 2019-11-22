package com.github.maybeec.jackson2545;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingGateway(name = "mailGateway", defaultRequestChannel = JMSConfig.EMAIL_QUEUE_IN)
public interface MailGateway {

  @Gateway
  void sendMail(@Payload JMSEmailTo email);
}

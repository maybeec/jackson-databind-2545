package com.github.maybeec.jackson2545;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class JMSEmailConsumer {

  private static final Logger LOG = LoggerFactory.getLogger(JMSEmailConsumer.class);

  public void sendMail(JMSEmailTo emailTo) {

    LOG.info("Processing mail from {}", JMSConfig.EMAIL_QUEUE);
  }
}

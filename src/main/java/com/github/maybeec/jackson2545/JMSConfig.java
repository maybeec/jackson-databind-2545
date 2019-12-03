package com.github.maybeec.jackson2545;

import javax.jms.QueueConnectionFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.jms.dsl.Jms;
import org.springframework.integration.support.json.JacksonJsonUtils;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.messaging.MessageChannel;
import org.springframework.retry.backoff.FixedBackOffPolicy;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.util.backoff.ExponentialBackOff;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JMSConfig {

  /** The oracle aq email queue name */
  public static final String EMAIL_QUEUE = "EMAIL_QUEUE";

  /** The spring integration email queue inbound */
  public static final String EMAIL_QUEUE_IN = "emailQueue.inbound";

  /** The spring integration email queue outbound */
  public static final String EMAIL_QUEUE_OUT = "emailQueue.outbound";

  @Bean
  public MessageConverter jacksonJmsMessageConverter() {

    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    converter.setTypeIdPropertyName("_type");
    ObjectMapper objectMapper = JacksonJsonUtils.messagingAwareMapper("com.github.maybeec");
    converter.setObjectMapper(objectMapper);
    return converter;
  }

  @Bean
  public JmsTemplate jmsTemplate(QueueConnectionFactory jmsConnectionFactory) throws Exception {

    JmsTemplate jmsTemplate = new JmsTemplate();
    jmsTemplate.setConnectionFactory(jmsConnectionFactory);
    jmsTemplate.setMessageConverter(jacksonJmsMessageConverter());
    jmsTemplate.setPubSubDomain(false);
    jmsTemplate.setDeliveryPersistent(true);
    return jmsTemplate;
  }

  @Bean(name = EMAIL_QUEUE_IN)
  public MessageChannel emailQueue(QueueConnectionFactory jmsConnectionFactory) {

    return Jms.pollableChannel(JMSConfig.EMAIL_QUEUE, jmsConnectionFactory)
        .jmsMessageConverter(jacksonJmsMessageConverter()).deliveryPersistent(true).sessionTransacted(true)
        .destination(JMSConfig.EMAIL_QUEUE).get();
  }

  @Bean
  public IntegrationFlow serviceActivatorJmsEmailConsumer(QueueConnectionFactory jmsConnectionFactory) {

    return IntegrationFlows.from(Jms.inboundGateway(jmsListenerContainer(jmsConnectionFactory)).autoStartup(true)
        .jmsMessageConverter(jacksonJmsMessageConverter()).id("emailInboundGateway").get()).get();
  }

  @Bean
  public AbstractMessageListenerContainer jmsListenerContainer(QueueConnectionFactory jmsConnectionFactory) {

    ExponentialBackOff exponentialBackOff = new ExponentialBackOff();
    return Jms.container(jmsConnectionFactory, JMSConfig.EMAIL_QUEUE).backOff(exponentialBackOff)
        .cacheLevel(DefaultMessageListenerContainer.CACHE_NONE).pubSubDomain(false).sessionTransacted(true).get();
  }
}

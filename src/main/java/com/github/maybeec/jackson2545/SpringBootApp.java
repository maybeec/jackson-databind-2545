package com.github.maybeec.jackson2545;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.github.maybeec")
public class SpringBootApp implements ApplicationRunner {

  @Autowired
  private MailGateway mailGateway;

  public static void main(String[] args) {

    SpringApplication.run(SpringBootApp.class, args);
  }

  @Override
  public void run(ApplicationArguments args) throws Exception {

    mailGateway.sendMail(new JMSEmailTo());
  }
}
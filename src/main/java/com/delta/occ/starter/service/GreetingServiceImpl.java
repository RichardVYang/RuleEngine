package com.delta.occ.starter.service;

import com.delta.foundation.mock.MockMessageChannel;
import com.delta.occ.starter.context.ApplicationContextProvider;
import com.delta.occ.starter.pojo.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import org.springframework.stereotype.Service;


@Service
public class GreetingServiceImpl implements GreetingService {

  private Logger log = LoggerFactory.getLogger(this.getClass().getName());

  /*
     Business logic should be implemented here.

     NOTE: Use classes in persistence package to do CRUD.
  */

  @Override
  public void processGreeting(Greeting greeting) {
    log.info("******** Entering GreetingServiceImpl::processGreeting...");
    // TODO  handle business logic here

    // Get the application context and channels defined in Foundation to forward message to other
    // service to process
    MockMessageChannel input =
        ApplicationContextProvider.getApplicationContext()
            .getBean("input", MockMessageChannel.class);
    MockMessageChannel output =
        ApplicationContextProvider.getApplicationContext()
            .getBean("output", MockMessageChannel.class);

    String payload =
        new StringBuffer()
            .append("ID:")
            .append(greeting.getId())
            .append(". greeting: ")
            .append(greeting.getMessage())
            .toString();

    Message<String> msg = MessageBuilder.withPayload(payload).build();
    input.send(msg); // /drop message to input channel

    log.info(
        "GreetingServiceImpl::processGreeting() Message count at output channel: "
            + output.getMessageCount());

    // TODO delegate results to persistence package classes to store in database
  }
}

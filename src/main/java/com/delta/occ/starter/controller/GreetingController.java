package com.delta.occ.starter.controller;

import com.delta.foundation.mock.MockMessageChannel;
import com.delta.occ.starter.pojo.Greeting;
import com.delta.occ.starter.context.ApplicationContextProvider;
import com.delta.occ.starter.service.GreetingServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping(path = "starter/v1/greeting")
public class GreetingController {

  @Autowired private GreetingServiceImpl greetingService;

  private Logger log = LoggerFactory.getLogger(this.getClass().getName());

  private static final String template =
      "Howdy, %s. Welcome to Spring Integration and Spring Boot world!";
  private final AtomicLong counter = new AtomicLong();

  /**
   * http://localhost:8080/starter/v1/greeting/person?name=James
   *
   * <p>Test data: {"id":1,"message":"Games Gosling"}
   */

  /*
  NOTE: Use this method only when you want to forward request to Foundation Integration Channels and no business logic is performed here.
   */

  @RequestMapping("/person")
  public Greeting greeting(@RequestParam(value = "name", defaultValue = "Sir") String name) {

    Greeting greeting = new Greeting(counter.incrementAndGet(), String.format(template, name));

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

    System.out.println("Message count at output channel: " + output.getMessageCount());

    return greeting;
  }

  /*
      Need to set header: Key=> Content-Type and Value => application/json
      Set body => {"id":1,"message":"Games Gosling"}
  http://localhost:8080/starter/v1/greeting//setgreeting
   */

  /*
  NOTE:  Do not implement business logic here.  Business logic should be implemented in the Service class.
   */

  @RequestMapping(
      method = RequestMethod.POST,
      value = "/setgreeting",
      produces = "application/json",
      consumes = "application/json")
  public Greeting setGreeting(@RequestBody Greeting greeting) {
    log.info("*** Entering GreetingController::setGreeting()....");

    greetingService.processGreeting(greeting);

    return greeting;
  }

  // http://localhost:8080/starter/v1/greeting//getgreeting?id=5

  /*
  NOTE:  Do not implement business logic here.  Business logic should be implemented in the Service class.
   */

  @RequestMapping(method = RequestMethod.GET, value = "/getgreeting", produces = "application/json")
  public Greeting getGreeting(@RequestParam(value = "id", required = true) String id) {
    log.info("*** Entering GreetingController::getGreeting() with id:" + id);
    String defaultGreeting = "Hello and welcome to Spring Boot and Spring Integration world!";
    Greeting greeting = new Greeting(Long.valueOf(id), defaultGreeting);

    greetingService.processGreeting(greeting);

    return greeting;
  }
}

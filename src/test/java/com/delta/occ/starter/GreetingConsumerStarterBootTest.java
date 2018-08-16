package com.delta.occ.starter;

import com.delta.foundation.mock.MockMessageChannel;
import com.delta.occ.starter.context.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import static org.junit.Assert.assertEquals;

/*
Start this as a combined Spring Integration and Spring Boot application. Tomcat is embedded by default and RESTful APIs can be created
to provide RESTful services. These services can then call the ApplicationContextProvider to get the context to retrieve various Spring Integration Channels, beans, and etc.
Also, JMX are provided. MBeans can be created to instrument, monitor, and change logging/auditing levels.
 */

// @SpringBootApplication(scanBasePackages = {"com.delta.occ.starter"})


@ImportResource({"classpath:GreetingConsumerStarterTestContext.xml"})
@SpringBootApplication
public class GreetingConsumerStarterBootTest {

  public static void main(String[] args) {
    Logger log = LoggerFactory.getLogger(GreetingConsumerStarterBootTest.class);

    SpringApplication.run(GreetingConsumerStarterBootTest.class, args);

    log.info(
        "Get the Spring Integration input and output channels (beans) provided by Spring Boot:");
    MockMessageChannel input =
        ApplicationContextProvider.getApplicationContext()
            .getBean("input", MockMessageChannel.class);
    MockMessageChannel output =
        ApplicationContextProvider.getApplicationContext()
            .getBean("output", MockMessageChannel.class);

    String payload = "Hello Message";

    Message<String> msg = MessageBuilder.withPayload(payload).build();
    input.send(msg);
    assertEquals(1, output.getMessageCount());
  }
}

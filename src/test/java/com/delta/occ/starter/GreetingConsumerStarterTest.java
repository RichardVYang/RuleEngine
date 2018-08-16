package com.delta.occ.starter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.delta.foundation.mock.MockMessageChannel;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

/*
Start this as a pure Spring Integration application
 */
public class GreetingConsumerStarterTest {

  @Test
  public void helloTest() {
    ClassPathXmlApplicationContext context =
        new ClassPathXmlApplicationContext("/"+
            GreetingConsumerStarterTest.class.getSimpleName() + "Context.xml",
            GreetingConsumerStarterTest.class);

    MockMessageChannel input = context.getBean("input", MockMessageChannel.class);
    MockMessageChannel output = context.getBean("output", MockMessageChannel.class);

    String payload = "Hello Message";

    Message<String> msg = MessageBuilder.withPayload(payload).build();
    input.send(msg);
    assertEquals(1, output.getMessageCount());

    context.close();
  }
}

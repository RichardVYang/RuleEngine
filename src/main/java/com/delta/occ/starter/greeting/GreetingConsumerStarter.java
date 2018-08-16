package com.delta.occ.starter.greeting;

import com.delta.foundation.core.AbstractConsumerProducer;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessagingException;

import java.time.Instant;

public class GreetingConsumerStarter extends AbstractConsumerProducer {

  @Override
  public MessageChannel getOutputChannel() {
    return null;
  }

  @Override
  protected void doStart() {}

  @Override
  protected void doStop() {}

  @Override
  public void handleMessage(Message<?> message) throws MessagingException {

    Message<String> msg = (Message<String>) message;
    String payload = msg.getPayload();
    final Instant now = Instant.now();
    logger.info("******************** Received greeting message '" + payload + "' at: " + now);

    outputChannel.send(msg);
  }
}

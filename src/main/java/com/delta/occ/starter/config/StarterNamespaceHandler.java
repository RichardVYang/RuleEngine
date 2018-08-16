package com.delta.occ.starter.config;

import com.delta.occ.starter.greeting.GreetingConsumerStarterParser;
import org.springframework.integration.config.xml.AbstractIntegrationNamespaceHandler;

public class StarterNamespaceHandler extends AbstractIntegrationNamespaceHandler {

  public void init() {
    this.registerBeanDefinitionParser(
        "greeting-consumer-starter", new GreetingConsumerStarterParser());
  }
}

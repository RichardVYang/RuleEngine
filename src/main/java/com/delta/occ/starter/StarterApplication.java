package com.delta.occ.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"classpath:GreetingConsumerStarterContext.xml"})
public class StarterApplication {

  public static void main(String[] args) {
    SpringApplication.run(StarterApplication.class, args);
  }
}

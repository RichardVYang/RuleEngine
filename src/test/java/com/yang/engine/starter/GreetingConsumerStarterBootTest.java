package com.yang.engine.starter;

import com.yang.engine.starter.context.ApplicationContextProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

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


  }
}

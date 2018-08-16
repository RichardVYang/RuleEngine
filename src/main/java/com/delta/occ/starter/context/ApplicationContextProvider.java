package com.delta.occ.starter.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/*
A useful static class to store and retrieve application context from anywhere.
 */
public class ApplicationContextProvider implements ApplicationContextAware {

  private static ApplicationContext context;

  public static ApplicationContext getApplicationContext() {
    return context;
  }

  @Override
  public void setApplicationContext(ApplicationContext context) throws BeansException {
    this.context = context;
  }
}

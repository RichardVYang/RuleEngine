package com.delta.occ.starter.pojo;

public class Greeting {

  private long id;
  private String message;

  public Greeting() {}

  public Greeting(long id, String message) {
    this.id = id;
    this.message = message;
  }

  public long getId() {
    return id;
  }

  public String getMessage() {
    return message;
  }

  public void setId(long id) {
    this.id = id;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}

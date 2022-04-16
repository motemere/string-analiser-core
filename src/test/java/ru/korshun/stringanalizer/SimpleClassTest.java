package ru.korshun.stringanalizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimpleClassTest {

  @Test
  void getGreeting() {
    assertEquals("Hello World!", SimpleClass.getGreeting());
  }

}

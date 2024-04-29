package org.venuspj.util.uuidProvider;


import java.util.UUID;
import org.junit.jupiter.api.Test;

public class UuidProviderTest {

  @Test
  public void randomUUID() throws Exception {
    UUID actual = UuidProvider.randomUUID();
    System.out.println("actual:" + actual);
    System.out.println("actual.toString().length():" + actual.toString().length());

  }
}

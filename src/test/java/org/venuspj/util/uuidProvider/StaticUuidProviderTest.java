package org.venuspj.util.uuidProvider;


import java.util.UUID;
import org.junit.jupiter.api.Test;

public class StaticUuidProviderTest {

  @Test
  public void randomUUID() throws Exception {
    StaticUuidProvider.initialise("95f4b3ef-5c26-4f1d-ba46-b9f49e00e3aa");
    UUID actual = UuidProvider.randomUUID();
    System.out.println("actual:" + actual);
  }

}

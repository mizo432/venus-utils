package org.venuspj.util.dateProvider;


import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class ShiftDateTimeProviderTest {

  @Test
  public void test01() throws Exception {
    ShiftDateTimeProvider.initialize(LocalDateTime.of(2017, 7, 1, 1, 2, 3, 4));
    LocalDateTime actual1 = DateProvider.currentLocalDateTime();
    System.out.println("actual1" + actual1);
    Thread.sleep(1000);
    LocalDateTime actual2 = DateProvider.currentLocalDateTime();
    System.out.println("actual2" + actual2);
    Thread.sleep(1000);
    LocalDateTime actual3 = DateProvider.currentLocalDateTime();
    System.out.println("actual3" + actual3);
  }
}

package org.venuspj.util.collect;


import org.junit.jupiter.api.Test;

public class CollectPreconditionsTest {


  @Test
  public void checkEntryNotNull3() throws Exception {
    CollectPreconditions.checkEntryNotNull("key", "value");

  }

  @Test
  public void checkNonNegative1() throws Exception {
    CollectPreconditions.checkNonNegative(1, "value");

  }

  @Test
  public void checkNonNegative2() throws Exception {
    CollectPreconditions.checkNonNegative(1L, "value");

  }

  @Test
  public void checkNonNegative3() throws Exception {
    CollectPreconditions.checkNonNegative(0, "value");

  }

  @Test
  public void checkNonNegative4() throws Exception {
    CollectPreconditions.checkNonNegative(0L, "value");

  }

  @Test
  public void checkPositive1() throws Exception {
    CollectPreconditions.checkPositive(1, "value");

  }


  @Test
  public void checkRemove1() throws Exception {
    CollectPreconditions.checkRemove(true);

  }

}

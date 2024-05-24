package org.venuspj.util.snowflake;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.venuspj.util.ipadress.StaticIpAddressProvider;

class NodeIdGeneratorTest {

  @Test
  void name() {
    StaticIpAddressProvider.initialize("1.2.3.4");
    long actualNodeId = NodeIdGenerator.generateNodeId();
    System.out.println(actualNodeId);
    assertThat(actualNodeId).isEqualTo(553);
    StaticIpAddressProvider.clear();
  }
}
package org.venuspj.util.snowflake;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.venuspj.util.dateProvider.StaticDateTimeProvider;

class SnowflakeIdGeneratorTest {

  @Test
  void testGenerateId() {
    // given
    StaticDateTimeProvider.initialize(LocalDateTime.of(2024, 8, 1, 1, 2, 3, 4000000));
    long nodeId = 100L;
    SnowflakeIdGenerator generator = new SnowflakeIdGenerator(nodeId);

    //when
    long actualId = generator.generateId();

    //then
    assertThat(actualId).isEqualTo(473883048166178816L);
    StaticDateTimeProvider.clear();
  }

}
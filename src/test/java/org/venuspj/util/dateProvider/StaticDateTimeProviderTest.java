package org.venuspj.util.dateProvider;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StaticDateTimeProviderTest {

  private final static Logger LOGGER = LoggerFactory.getLogger(StaticDateTimeProviderTest.class);

  @BeforeEach
  public void setUp() {
    StaticDateTimeProvider.initialize(LocalDateTime.of(2012, 3, 4, 5, 6, 7, 8));
  }

  @AfterEach
  void tearDown() {
    StaticDateTimeProvider.clear();
  }

  @Test
  public void initialize() throws Exception {
    LocalDateTime actual = DateProvider.currentLocalDateTime();
    LOGGER.debug("actual:" + actual);
    assertThat(actual).isNotNull();
    assertThat(actual.toString()).isEqualTo("2012-03-04T05:06:07.000000008");
  }


}
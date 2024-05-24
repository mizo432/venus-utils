package org.venuspj.util.dateProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import org.assertj.core.api.Java6Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 */
public class DateProviderTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(DateProviderTest.class);

  @Test
  public void currentDateTime() throws Exception {
    LocalDateTime actual = DateProvider.currentLocalDateTime();
    Java6Assertions.assertThat(actual).isNotNull();
    LOGGER.debug("actual:" + actual);
  }

  @Test
  public void currentDate() throws Exception {
    LocalDate actual = DateProvider.currentLocalDate();
    Java6Assertions.assertThat(actual).isNotNull();
    LOGGER.debug("actual:" + actual);
  }

  @Test
  public void currentTime() throws Exception {
    LocalTime actual = DateProvider.currentLocalTime();
    Java6Assertions.assertThat(actual).isNotNull();
    LOGGER.debug("actual:" + actual);
  }

  @Test
  public void currentYearMonth() throws Exception {
    YearMonth actual = DateProvider.currentYearMonth();
    Java6Assertions.assertThat(actual).isNotNull();
    LOGGER.debug("actual:" + actual);
  }

  @Test
  public void currentMonthDay() throws Exception {
    MonthDay actual = DateProvider.currentMonthDay();
    Java6Assertions.assertThat(actual).isNotNull();
    LOGGER.debug("actual:" + actual);
  }


}
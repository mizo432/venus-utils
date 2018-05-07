package org.venuspj.util.dateProvider;

import org.assertj.core.api.Java6Assertions;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

/**
 */
public class DateProviderTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateProviderTest.class);

    @Test
    public void currentDateTime() throws Exception {
        LocalDateTime actual = DateProvider.currentDateTime();
        Java6Assertions.assertThat(actual).isNotNull();
        LOGGER.debug("actual:" + actual);
    }

    @Test
    public void currentDate() throws Exception {
        LocalDate actual = DateProvider.currentDate();
        Java6Assertions.assertThat(actual).isNotNull();
        LOGGER.debug("actual:" + actual);
    }

    @Test
    public void currentTime() throws Exception {
        LocalTime actual = DateProvider.currentTime();
        Java6Assertions.assertThat(actual).isNotNull();
        LOGGER.debug("actual:" + actual);
    }

    @Test
    public void currentYearMonth() throws Exception {
        YearMonth actual = DateProvider.currentYearMonth();
        Java6Assertions.assertThat(actual).isNotNull();
        LOGGER.debug("actual:" + actual);
    }


}
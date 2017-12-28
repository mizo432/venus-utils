package org.venuspj.util.dateProvider;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;

import static org.assertj.core.api.Java6Assertions.assertThat;

/**
 */
public class DateProviderTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateProviderTest.class);

    @Test
    public void currentDateTime() throws Exception {
        LocalDateTime actual = DateProvider.currentDateTime();
        assertThat(actual).isNotNull();
        LOGGER.debug("actual:" + actual);
    }

    @Test
    public void currentDate() throws Exception {
        LocalDate actual = DateProvider.currentDate();
        assertThat(actual).isNotNull();
        LOGGER.debug("actual:" + actual);
    }

    @Test
    public void currentTime() throws Exception {
        LocalTime actual = DateProvider.currentTime();
        assertThat(actual).isNotNull();
        LOGGER.debug("actual:" + actual);
    }

    @Test
    public void currentYearMonth() throws Exception {
        YearMonth actual = DateProvider.currentYearMonth();
        assertThat(actual).isNotNull();
        LOGGER.debug("actual:" + actual);
    }


}
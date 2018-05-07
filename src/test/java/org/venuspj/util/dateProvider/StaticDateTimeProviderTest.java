package org.venuspj.util.dateProvider;

import org.assertj.core.api.Java6Assertions;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 */
public class StaticDateTimeProviderTest {

    private static Logger LOGGER = LoggerFactory.getLogger(StaticDateTimeProviderTest.class);

    @Before
    public void setUp() {
        StaticDateTimeProvider.initialize(LocalDateTime.of(2012, 3, 4, 5, 6, 7, 8));
    }

    @Test
    public void initialize() throws Exception {
        LocalDateTime actual = DateProvider.currentDateTime();
        LOGGER.debug("actual:" + actual);
        Java6Assertions.assertThat(actual).isNotNull();
        Java6Assertions.assertThat(actual.toString()).isEqualTo("2012-03-04T05:06:07.000000008");
    }


}
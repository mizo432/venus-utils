package org.venuspj.util.dateProvider;

import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StaticDateProviderTest {

    @Test
    public void test01() throws Exception {
        StaticDateProvider.initialize(LocalDate.of(2017,7,1));
        LocalDateTime actual1 = DateProvider.currentLocalDateTime();
        System.out.println("actual1:" + actual1);
        Thread.sleep(1000);
        LocalDateTime actual2 = DateProvider.currentLocalDateTime();
        System.out.println("actual2:" + actual2);
        Thread.sleep(1000);
        LocalDateTime actual3 = DateProvider.currentLocalDateTime();
        System.out.println("actual3:" + actual3);
    }
}

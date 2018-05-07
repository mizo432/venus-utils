package org.venuspj.util.dateProvider;

import org.junit.Test;

import java.time.LocalDateTime;

public class ShiftDateTimeProviderTest {

    @Test
    public void test01() throws Exception {
        ShiftDateTimeProvider.initialize(LocalDateTime.of(2017,7,1,1,2,3,4));
        LocalDateTime actual1 = DateProvider.currentDateTime();
        System.out.println("actual1" + actual1);
        Thread.sleep(1000);
        LocalDateTime actual2 = DateProvider.currentDateTime();
        System.out.println("actual2" + actual2);
        Thread.sleep(1000);
        LocalDateTime actual3 = DateProvider.currentDateTime();
        System.out.println("actual3" + actual3);
    }
}

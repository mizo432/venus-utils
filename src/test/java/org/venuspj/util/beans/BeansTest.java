package org.venuspj.util.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import org.junit.jupiter.api.Test;

class BeansTest {

  @Test
  void test01() {
    Sample1 src = new Sample1();
    src.setBoolean0(true);
    src.setBoolean1(true);
    src.setBoolean2("1");
    src.setInt0(1);
    src.setInt1(1);
    src.setInt2("1");
    src.setLocalDate0(LocalDate.now());
    src.setLocalDate1(LocalDate.now());
    src.setLocalDate2("2024-05-01");
    src.setLocalDateTime0(LocalDateTime.now());
    src.setLocalDateTime1(LocalDateTime.now());
    src.setLocalDateTime2("20240501131415");
    src.setLocalTime0(LocalTime.now());
    src.setLocalTime1(LocalTime.now());
    src.setLocalTime2("131415");
    src.setYearMonth0(YearMonth.now());
    src.setYearMonth1(YearMonth.now());
    src.setYearMonth2("202501");
    src.setMonthDay0(MonthDay.now());
    src.setMonthDay1(MonthDay.now());
    src.setMonthDay2("1231");
    src.setEnum0(Sex.女);
    src.setEnum1(Sex.女);
    src.setEnum2("女");

    Sample2 dest = Beans
        .createAndCopy(Sample2.class, src)
        .localDateConverter("yyyy-MM-dd", "localDate1", "localDate2")
        .localDateTimeConverter("yyyyMMddHHmmss", "localDateTime1", "localDateTime2")
        .localTimeConverter("HHmmss", "localTime1", "localTime2")
        .yearMonthConverter("yyyyMM", "yearMonth1", "yearMonth2")
        .monthDayConverter("MMdd", "monthDay1", "monthDay2")
        .execute();

    System.out.println(dest);
  }
}
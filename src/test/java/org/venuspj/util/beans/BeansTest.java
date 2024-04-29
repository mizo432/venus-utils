package org.venuspj.util.beans;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.YearMonth;
import java.util.Date;
import org.junit.jupiter.api.Test;

class BeansTest {

  @Test
  void test01() {
    Sample1 src = new Sample1();
    src.setInt0(1);
    src.setInt1(1);
    src.setInt2("1");
    src.setDate0(new Date());
    src.setDate1(new Date());
    src.setDate2("20240501");
    src.setSqlDate0(new java.sql.Date(new Date().getTime()));
    src.setSqlDate1(new java.sql.Date(new Date().getTime()));
    src.setSqlDate2("20240501");
    src.setTimestamp0(new Timestamp(new Date().getTime()));
    src.setTimestamp1(new Timestamp(new Date().getTime()));
    src.setTimestamp2("20240501");
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

    Sample2 dest = Beans
        .createAndCopy(Sample2.class, src)
        .dateConverter("yyyyMMdd", "date1", "date2")
        .sqlDateConverter("yyyyMMdd", "sqlDate1", "sqlDate2")
        .timestampConverter("yyyyMMdd", "timestamp1", "timestamp2")
        .localDateConverter("yyyy-MM-dd", "localDate1", "localDate2")
        .localDateTimeConverter("yyyyMMddHHmmss", "localDateTime1", "localDateTime2")
        .localTimeConverter("HHmmss", "localTime1", "localTime2")
        .yearMonthConverter("yyyyMM", "yearMonth1", "yearMonth2")
        .monthDayConverter("MMdd", "monthDay1", "monthDay2")
        .execute();

    System.out.println(dest);
  }
}
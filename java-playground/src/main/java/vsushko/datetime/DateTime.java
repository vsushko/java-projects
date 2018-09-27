package vsushko.datetime;

import java.time.*;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.nextOrSame;

public class DateTime {

    public static void main(String[] args) {
        // creating a LocalDate and reading its values
        LocalDate date = LocalDate.of(2014, 3, 18);
        int year = date.getYear();
        System.out.println(year);
        Month month = date.getMonth();
        System.out.println(month);
        int day = date.getDayOfMonth();
        System.out.println(day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        System.out.println(dayOfWeek);
        int len = date.lengthOfMonth();
        System.out.println(len);
        boolean leap = date.isLeapYear();
        System.out.println(leap);

        // reading LocalDate values using s TemporalField
        LocalDate today = LocalDate.now();
        System.out.println(today);

        year = date.get(ChronoField.YEAR);
        System.out.println(year);

        int monthOfYear = date.get(ChronoField.MONTH_OF_YEAR);
        System.out.println(monthOfYear);

        day = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println(day);

        // creating a LocalTime and reading its values
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        System.out.println(hour);
        int minute = time.getMinute();
        System.out.println(minute);
        int second = time.getSecond();
        System.out.println(second);

        // creating using static parse methods
        LocalDate parsedDate = LocalDate.parse("2014-03-18");
        System.out.println(parsedDate);

        LocalTime parsedTime = LocalTime.parse("13:45:20");
        System.out.println(parsedTime);

        // creating a LocalDateTime directly or by combining a date and time
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20);
        System.out.println(dt1);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        System.out.println(dt2);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        System.out.println(dt3);
        LocalDateTime dt4 = date.atTime(time);
        System.out.println(dt4);
        LocalDateTime dt5 = time.atDate(date);
        System.out.println(dt5);

        LocalDate date1 = dt1.toLocalDate();
        System.out.println(date1);
        LocalTime time1 = dt1.toLocalTime();
        System.out.println(time1);

        // instant: a date and time for machines
        System.out.println(Instant.ofEpochSecond(3));
        System.out.println(Instant.ofEpochSecond(3, 0));
        System.out.println(Instant.ofEpochSecond(2, 1_000_000_000));
        System.out.println(Instant.ofEpochSecond(3, -1_000_000_000));

        // will throw an UnsupportedTemporalTypeException exception
        // int instantDay = Instant.now().get(ChronoField.DAY_OF_MONTH);
        // System.out.println(instantDay);

        // Defining a Duration or a Period
        Duration d1 = Duration.between(LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 20),
                LocalDateTime.of(2014, Month.MARCH, 18, 13, 45, 21));
        System.out.println("d1: " + d1.getSeconds());
        // another examples with different Temporal
        // Duration d2 = Duration.between(dateTime1, dateTime2);
        // Duration d3 = Duration.between(instant1, instant2);

        Period tenDays = Period.between(LocalDate.of(2014, 3, 8), LocalDate.of(2014, 3, 18));
        System.out.println(tenDays);

        // creating Durations and Periods
        Duration threeMinutes1 = Duration.ofMinutes(3);
        System.out.println(threeMinutes1);
        Duration threeMinutes2 = Duration.of(3, ChronoUnit.MINUTES);
        System.out.println(threeMinutes2);

        // Using the predefined TemporalAdjusters
        // method with() creates a copy of this temporal object with part of the state changed
        LocalDate localDate1 = LocalDate.of(2014, 3, 18);
        System.out.println(localDate1);
        // creates a new date set to the first occurrence of the specified day of week
        LocalDate localDate2 = localDate1.with(nextOrSame(DayOfWeek.SUNDAY));
        System.out.println(localDate2);
        // creates a new date set to the last day of the current month
        LocalDate localDate3 = localDate2.with(lastDayOfMonth());
        System.out.println(localDate3);
    }
}

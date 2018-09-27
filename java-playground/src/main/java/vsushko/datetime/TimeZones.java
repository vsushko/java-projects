package vsushko.datetime;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.chrono.JapaneseDate;
import java.util.Locale;
import java.util.TimeZone;

public class TimeZones {

    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);

        ZoneId romeZone = ZoneId.of("Europe/Rome");
        ZoneId zoneId = TimeZone.getDefault().toZoneId();

        // applying a time zone to a point in time
        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);
        System.out.println(zdt1);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);
        System.out.println(zdt2);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);
        System.out.println(zdt3);

        // convert a LocalDateTime to an Instant by using a ZoneId
        dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);

        // fixed offset from UTC/Greenwich
        ZoneOffset newYorkOffset = ZoneOffset.of("-05:00");

        Instant instantFromDateTime = dateTime.toInstant(newYorkOffset);
        System.out.println(instantFromDateTime);

        // outer way around
        Instant instantNow = Instant.now();
        LocalDateTime timeFromInstant = LocalDateTime.ofInstant(instant, romeZone);
        System.out.println(timeFromInstant);

        LocalDate.of(2014, Month.MARCH, 18);
        JapaneseDate japaneseDate = JapaneseDate.from(date);
        System.out.println(japaneseDate);

        Chronology japaneseChronology = Chronology.ofLocale(Locale.JAPAN);
        ChronoLocalDate now = japaneseChronology.dateNow();
        System.out.println(now);


    }
}

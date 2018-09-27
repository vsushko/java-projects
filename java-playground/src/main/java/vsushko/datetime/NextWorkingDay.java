package vsushko.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.*;

public class NextWorkingDay implements TemporalAdjuster {

    @Override
    public Temporal adjustInto(Temporal temporal) {
        // read the current day
        DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        // normally add one day
        int dayToAdd = 1;
        if (dayOfWeek == DayOfWeek.FRIDAY) {
            dayToAdd = 3;
        } else if (dayOfWeek == DayOfWeek.SATURDAY) {
            dayToAdd = 2;
        }
        // return the modified date adding the right number of days
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }

    public static void main(String[] args) {
        // pass with lambda expression
        LocalDate localDate = LocalDate.of(2014, 3, 18);
        localDate.with(temporal -> {
            int dayToAdd = 1;
            DayOfWeek dayOfWeek = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            if (dayOfWeek == DayOfWeek.FRIDAY) dayToAdd = 3;
            else if (dayOfWeek == DayOfWeek.SATURDAY) dayToAdd = 2; return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });

        // defines the TemporalAdjuster with a lambda expression
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(temporal -> {
            DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) dayToAdd = 3;
            if (dow == DayOfWeek.SATURDAY) dayToAdd = 2; return temporal.plus(dayToAdd, ChronoUnit.DAYS);
        });
        localDate = localDate.with(nextWorkingDay);
        System.out.println(localDate);
    }
}

package vsushko.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

public class PrintingAndParsingDateTimeObjects {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        LocalDate date1 = LocalDate.parse("20140318", DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18", DateTimeFormatter.ISO_LOCAL_DATE);

        // creating a DateTimeFormatter from a pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dateToFormat = LocalDate.of(2014, 3, 18);
        String formattedDate = dateToFormat.format(formatter);
        System.out.println(formattedDate);
        LocalDate formattedLocalDate = LocalDate.parse(formattedDate, formatter);
        System.out.println(formattedLocalDate);

        // creating a localized DateTimeFormatter
        DateTimeFormatter italianFormatter =
                DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.ITALIAN);
        LocalDate someDate = LocalDate.of(2014, 3, 18);
        System.out.println(someDate);
        formattedDate = date.format(italianFormatter); // 18. marzo 2014
        System.out.println(formattedDate);
        LocalDate localizedDate = LocalDate.parse(formattedDate, italianFormatter);
        System.out.println(localizedDate);

        // building a DateTimeFormatter
        DateTimeFormatter italianFormatterTwo = new DateTimeFormatterBuilder()
                .appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(". ")
                .appendText(ChronoField.MONTH_OF_YEAR)
                .appendLiteral(" ")
                .appendText(ChronoField.YEAR)
                .parseCaseInsensitive()
                .toFormatter(Locale.ITALIAN);

        System.out.println(italianFormatterTwo);
    }
}

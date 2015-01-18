package mcb.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class DateFormatter {
  private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance();

  public static String formatDate(Date date) {
    synchronized (DateFormatter.DATE_FORMAT) {
      return DateFormatter.DATE_FORMAT.format(date);
    }
  }

  public static Date parseDate(String string) throws ParseException {
    synchronized (DateFormatter.DATE_FORMAT) {
      return DateFormatter.DATE_FORMAT.parse(string);
    }
  }

}

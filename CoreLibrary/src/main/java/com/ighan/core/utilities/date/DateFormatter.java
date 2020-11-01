package com.ighan.core.utilities.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {

    public enum DateTimePattern {
        DATE_TIME_SECONDS("yyyy-MM-dd HH:mm:ss"),
        DATE_TIME_NO_SECONDS("yyyy-MM-dd HH:mm"),
        DATE_NO_TIME_NO_SECONDS("yyyy-MM-dd"),
        NO_DATE_TIME_SECONDS("HH:mm:ss"),
        NO_DATE_TIME_NO_SECONDS("HH:mm");

        private String pattern;

        DateTimePattern(String pattern) {
            this.pattern = pattern;
        }

        public String getPattern() {
            return pattern;
        }
    }

    public static String getString(TimeSpan time, DateTimePattern pattern) {
        Date date = new Date();
        date.setHours(time.getHours());
        date.setMinutes(time.getMinutes());
        date.setSeconds((int) time.getSeconds());
        return getString(date, pattern);
    }

    public static String getString(Date date, DateTimePattern pattern) {
        return new SimpleDateFormat(pattern.getPattern()).format(date);
    }

    public static Date parseDate(String date, DateTimePattern pattern) throws ParseException {
        return new SimpleDateFormat(pattern.getPattern(), Locale.US).parse(date);
    }

    public static Date getDateOnly(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}

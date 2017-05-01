package com.konkow.framework.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class DateUtils {

    private static final ThreadLocal<Map<String, Map<Locale, DateFormat>>> FORMAT_LOCALES = new ThreadLocal<Map<String, Map<Locale, DateFormat>>>() {
        @Override
        protected Map<String, Map<Locale, DateFormat>> initialValue() {
            return new HashMap<String, Map<Locale, DateFormat>>();
        }
    };
    public static final Date FIRST = create("1900-01-01");
    public static final Date LAST = create("3000-12-31");
    public static final long ONE_DAY = 24 * 60 * 60 * 1000L;

    public static final String DEFAULT_FORMAT = "yyyy-MM-dd";

    private DateUtils() {
    }

    /**
     * Gets date format.
     *
     * @param format
     *            format
     * @param locale
     *            locale
     * @return date format
     */
    public static DateFormat getDateFormat(String format, Locale locale) {
        Map<String, Map<Locale, DateFormat>> map = FORMAT_LOCALES.get();
        if (map.containsKey(format)) {
            if (map.get(format).containsKey(locale)) {
                return map.get(format).get(locale);
            } else {
                return createDateFormat(map, format, locale);
            }
        } else {
            map.put(format, new HashMap<Locale, DateFormat>());
            return createDateFormat(map, format, locale);
        }
    }

    private static DateFormat createDateFormat(Map<String, Map<Locale, DateFormat>> map, String format, Locale locale) {
        DateFormat df = new SimpleDateFormat(format, locale);
        map.get(format).put(locale, df);
        return df;
    }

    private static Date create(String dateString) {
        try {
            return getDateFormat(DEFAULT_FORMAT, Locale.US).parse(dateString);
        } catch (Exception e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    public static String format(Date date) {
        return getDateFormat(DEFAULT_FORMAT, Locale.US).format(date);
    }

    public static String format(Date date, String format) {
        return getDateFormat(format, Locale.US).format(date);
    }

    public static Date parse(String dateString) {
        try {
            return getDateFormat(DEFAULT_FORMAT, Locale.US).parse(dateString);
        } catch (Exception e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    public static Date parse(String dateString, String format) {
        try {
            return getDateFormat(format, Locale.US).parse(dateString);
        } catch (Exception e) {
            throw ExceptionUtils.wrap(e);
        }
    }

    public static String convertDateToBase(String dat) {

        try {
            Date date = parse(dat, "yyyy-MM-dd");

            return format(date, "yyyyMMdd");
        } catch (Exception ex) {
            return null;
        }
    }

    public static String convertDateToBase(Date date) {

        try {
            return format(date, "yyyyMMdd");
        } catch (Exception ex) {
            return null;
        }
    }

    public static String convertDateToShow(String dat) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            Date date = format.parse(dat);
            return format(date, "dd/MM/yyyy"); // dat.substring(0, 3)+"-"+dat.substring(4, 5)+"-"+dat.substring(6, 7);
        } catch (Exception ex) {
            return null;
        }

    }

    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(cal.getTime());
    }

    public static String convertExcelDateToBase(String dat) {
        try {
            Date date = parse(dat, "dd/MM/yyyy");

            return format(date, "yyyyMMdd");
        } catch (Exception ex) {
            return null;
        }
    }

    public static Date addDays(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); // minus number would decrement the days
        return cal.getTime();
    }
}

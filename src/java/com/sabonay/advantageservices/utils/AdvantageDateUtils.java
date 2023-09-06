package com.sabonay.advantageservices.utils;

import com.ctrloption.formating.DateTimeUtils;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;

/**
 * @author Daud Ainoo
 * @Company Sabonay
 * @Contact 0245 293945
 * @Website https://sabonay.com
 * @date Sun 20 Aug, 2023 13:01 pm
 */
public class AdvantageDateUtils extends DateTimeUtils {

    private static final Logger log = Logger.getLogger(AdvantageDateUtils.class.getName());

//    public static int monthInInteger(String dateMonth) {
//        int month;
//        try {
//            Date date = new SimpleDateFormat("MMMM", Locale.UK).parse(dateMonth);
//            DateTime dt = new DateTime(date);
//            month = dt.getMonthOfYear();
//        } catch (ParseException e) {
//            AppLogger.error(log, e, "error converting dateMonth " + dateMonth);
//            return 0;
//        }
//        return month;
//    }
    public static int getMonthDifference(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int yearsDiff = endCal.get(Calendar.YEAR) - startCal.get(Calendar.YEAR);
        int monthsDiff = endCal.get(Calendar.MONTH) - startCal.get(Calendar.MONTH);
        return yearsDiff * 12 + monthsDiff;
    }

    public static Date getFirstDayDateFromMonthAndYear(int monthNumber, int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, monthNumber - 1, 1);
        return cal.getTime();
    }

    public static Date getFirstDayDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        return cal.getTime();
    }

    public static int getMonthNumberFromMonthName(String monthName) {
        return Month.valueOf(monthName.toUpperCase()).getValue();
    }

    public static String getMonthNameFromMonthNumber(int monthNumber) {
        return Month.of(monthNumber).name();
    }
}

package com.martsforever.core.test;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestCalDate {

    @Test
    public void test1() throws ParseException {

        try {
            int dur = getDurBetweenDay("2018-03-21","2018-03-20");
            System.out.println(dur);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取本周第一天
     *
     * @param date
     * @return
     */
    public static String getWeekStartTimeByDate(Date date) {
        Calendar cal = Calendar.getInstance();

        cal.setTime(date);

        int d = 0;
        if (cal.get(Calendar.DAY_OF_WEEK) == 1) {
            d = -6;
        } else {
            d = 2 - cal.get(Calendar.DAY_OF_WEEK);
        }
        cal.add(Calendar.DAY_OF_WEEK, d);

        return new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());
    }

    /**
     * 获取给定日期的上一个星期六日期字符串
     *
     * @param dateStr
     * @return
     */
    public static String getLastSaturdayDateStr(String dateStr) throws ParseException {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String mondayDateStr = getWeekStartTimeByDate(df.parse(dateStr));
        Date mondayDate = df.parse(mondayDateStr);
        cal.setTime(mondayDate);
        cal.add(Calendar.DATE, -2);
        Date lastSaturday = cal.getTime();
        return df.format(lastSaturday);
    }

    public static int getDurBetweenDay(String dateStr1, String dateStr2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d1 = sdf.parse(dateStr1);
        Date d2 = sdf.parse(dateStr2);
        return (int) ((d1.getTime() - d2.getTime()) / (1000 * 24 * 60 * 60));
    }

}

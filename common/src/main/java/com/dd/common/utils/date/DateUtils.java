package com.dd.common.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    private static SimpleDateFormat getDateFormat(String format) {
        return new SimpleDateFormat(format);
    }

    /**
     * 将时间类型转换成指定格式的字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {
        return getDateFormat(format).format(date);
    }

    /**
     * 将时间类型转换成指定格式的字符串
     *
     * @param date
     * @param dateStyle
     * @return
     */
    public static String dateToString(Date date, DateStyle dateStyle) {
        return getDateFormat(dateStyle.getValue()).format(date);
    }

    /**
     * 将指定格式的字符串转换成时间类型
     *
     * @param date
     * @param format
     * @return
     */
    public static Date stringToDate(String date, String format) {
        try {
            return getDateFormat(format).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将指定格式的字符串转换成时间类型
     *
     * @param date
     * @param dateStyle
     * @return
     */
    public static Date stringToDate(String date, DateStyle dateStyle) {
        try {
            return getDateFormat(dateStyle.getValue()).parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取offset天后的时间（offset可为负数）
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date getNextDay(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, offset);
        return calendar.getTime();
    }

    /**
     * 获取offset月后的时间（offset可为负数）
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date getNextMonth(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, offset);
        return calendar.getTime();
    }

    /**
     * 获取offset年后的时间（offset可为负数）
     *
     * @param date
     * @param offset
     * @return
     */
    public static Date getNextYear(Date date, int offset) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, offset);
        return calendar.getTime();
    }

    /**
     * 将秒数转成字符串
     *
     * @param sec
     * @return
     */
    public static String secToStr(Integer sec) {
        int hour = sec / (60 * 60);
        int minute = (sec % (60 * 60)) / 60;
        int second = (sec % (60 * 60)) % 60;
        StringBuilder builder = new StringBuilder();
        if (hour != 0) {
            builder.append(hour).append("时");
        }
        if (minute != 0) {
            builder.append(minute).append("分");
        }
        builder.append(second).append("秒");
        return builder.toString();
    }

    /**
     * 获取当天时间字符串
     *
     * @param dateStyle
     * @return
     */
    public static String getTodayStr(DateStyle dateStyle) {
        return dateToString(new Date(), dateStyle);
    }

    /**
     * 获取日期是星期几
     *
     * @param date
     * @return
     */
    public static Integer getDayOfWeek(Date date) {
        Calendar c= Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            weekday = 7;
        } else {
            weekday -= 1;
        }
        return weekday;
    }

    /**
     * 获取今天是星期几
     *
     * @return
     */
    public static Integer getTodayOfWeek() {
        return getDayOfWeek(new Date());
    }

    /**
     * 根据日期获取星期几
     *
     * @param date
     * @return
     */
    public static String getDayOfWeekStr(String date) {
        Integer dayOfWeek = getDayOfWeek(stringToDate(date, DateStyle.YYYY_MM_DD));
        if (dayOfWeek == 1) {
            return "星期一";
        } else if (dayOfWeek == 2) {
            return "星期二";
        } else if (dayOfWeek == 3) {
            return "星期三";
        } else if (dayOfWeek == 4) {
            return "星期四";
        } else if (dayOfWeek == 5) {
            return "星期五";
        } else if (dayOfWeek == 6) {
            return "星期六";
        } else {
            return "星期日";
        }
    }

    /**
     * 获取指定年月的第一天
     * @param year
     * @param month
     * @return
     */
    public static String getFirstDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定年月的最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定年月的第一天
     * @param year
     * @return
     */
    public static String getFirstDayOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, 0);
        //获取某月最小天数
        int firstDay = cal.getMinimum(Calendar.DATE);
        //设置日历中月份的最小天数
        cal.set(Calendar.DAY_OF_MONTH,firstDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    /**
     * 获取指定年月的最后一天
     * @param year
     * @return
     */
    public static String getLastDayOfYear(int year) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, 11);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DATE);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }

    public static void main(String[] args) {
        Date nextDay = getNextDay(new Date(), -1);
        System.out.println(dateToString(nextDay, DateStyle.YYYY_MM_DD));
    }

}

package com.test.util;

import com.ctrip.framework.apollo.core.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className CommonDateUtils
 * @description 日期相关工具类 全部基于Java8
 * @date 2020/1/17 17:53
 */
@Slf4j
public class CommonDateUtils {

    /**
     * yyMMdd 年月日（年取后两位，如：200117）
     */
    public static final String YY_MM_DD = "yyMMdd";

    /**
     * HHmmss 时分秒
     */
    public static final String HH_MM_SS = "HHmmss";

    /**
     * yyyyMMddHHmmss 年月日时分秒
     */
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyyMMddHHmmss";

    /**
     * 年月日-获取当前日期
     */
    public static final LocalDate LOCAL_DATE = LocalDate.now();

    /**
     * 时分秒-获取当前日期
     */
    public static final LocalTime LOCAL_TIME = LocalTime.now();

    /**
     * 年月日时分秒-获取当前日期
     */
    public static final LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();


    /**
     * @description 根据年月日自定义日期
     * @author zhengchunfeng
     * @date 2020/1/17 17:57
     * @param year 1
     * @param month 2
     * @param dayOfMonth 3
     * @return java.time.LocalDate
     **/
    public static LocalDate getLocalDateByInit(int year, int month, int dayOfMonth) {

        LocalDate localDate = LocalDate.of(year, month, dayOfMonth);
        return localDate;
    }



    /**
     * @description 判断两个日期是否相等
     * @author zhengchunfeng
     * @date 2020/1/17 17:59
     * @param localDate1 1
     * @param localDate2 2
     * @return boolean
     **/
    public static boolean isEqualsLocalDate(LocalDate localDate1, LocalDate localDate2){

        if(localDate1 == null || localDate2 == null){
            return false;
        }
        return localDate1.isEqual(localDate2);
    }


    /**
     * @description 计算一年前日期
     * @author zhengchunfeng
     * @date 2020/1/17 18:03
     * @param number 1
     * @return java.time.LocalDate
     **/
    public static LocalDate getPreviousYearDate(int number){

        LocalDate today = LocalDate.now();
        LocalDate previousYear = today.minus(number, ChronoUnit.YEARS);
        return previousYear;
    }


    /**
     * @description 计算一年后日期
     * @author zhengchunfeng
     * @date 2020/1/17 18:03
     * @param number 1
     * @return java.time.LocalDate
     **/
    public static LocalDate getNextYearDate(int number){

        LocalDate today = LocalDate.now();
        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);
        return nextYear;
    }


    /**
     * @description 日期是否早于另一个日期
     * @author zhengchunfeng
     * @date 2020/1/17 18:29
     * @param localDate1 1
     * @param localDate2 2
     * @return boolean
     **/
    public static boolean isBeforeDate(LocalDate localDate1, LocalDate localDate2){

        if(localDate1 == null || localDate2 == null){
            return false;
        }
        return localDate1.isBefore(localDate2);

    }


    /**
     * @description 日期是否晚于另一个日期
     * @author zhengchunfeng
     * @date 2020/1/17 18:29
     * @param localDate1 1
     * @param localDate2 2
     * @return boolean
     **/
    public static boolean isAfterDate(LocalDate localDate1, LocalDate localDate2){

        if(localDate1 == null || localDate2 == null){
            return false;
        }
        return localDate1.isAfter(localDate2);

    }

    /**
     * @description 判断当前年份是否闰年
     * @author zhengchunfeng
     * @date 2020/1/17 18:33
     * @return boolean
     **/
    public static boolean isLeapYearDate(){
        LocalDate today = LocalDate.now();
        return today.isLeapYear();

    }

    /**
     * @description 获取当前纽约时间
     * @author zhengchunfeng
     * @date  18:35
     * @return java.time.ZonedDateTime
     **/
    public static ZonedDateTime getZonedDateTime(){

        // Java 8不仅分离了日期和时间，也把时区分离出来了。现在有一系列单独的类如ZoneId来处理特定时区，ZoneDateTime类来表示某时区下的时间。
        // 这在Java 8以前都是 GregorianCalendar类来做的。下面这个例子展示了如何把本时区的时间转换成另一个时区的时间。
        ZoneId america = ZoneId.of("America/New_York");
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime dateAndTimeInNewYork  = ZonedDateTime.of(localDateTime, america );
        return dateAndTimeInNewYork;

    }


    /**
     * @description 计算两个日期的间隔（月数，周数，天数）
     * @author zhengchunfeng
     * @date 2020/1/17 18:38
     * @return int
     **/
    public static int calInterValBetween(LocalDate localDate){

        LocalDate today = LocalDate.now();
        Period period = Period.between(today, localDate);
        // 返回间隔天数
        return period.getDays();
    }

    /**
     * @description 获取当前时间戳
     * @author zhengchunfeng
     * @date 2020/1/17 18:40
     * @return long
     **/
    public static long getTimestamp(){

        // 时间戳信息里同时包含了日期和时间，这和java.config.Date很像。实际上Instant类确实等同于 Java 8之前的Date类，
        // 你可以使用Date类和Instant类各自的转换方法互相转换，
        // 例如：Date.from(Instant) 将Instant转换成java.config.Date，Date.toInstant()则是将Date类转换成Instant类。
        Instant timestamp = Instant.now();
        return timestamp.toEpochMilli();
    }


    /**
     * @description 获取yy_mm_dd
     * @author zhengchunfeng
     * @date 2020/1/17 18:45
     * @return java.lang.String
     **/
    public static String getYY_MM_DD(){

        try {
            DateTimeFormatter SimpleDateFormat = DateTimeFormatter.ofPattern(YY_MM_DD);
            return SimpleDateFormat.format(LOCAL_DATE);
        } catch (Exception e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }

    }


   /**
    * @description 获取当前日期HHmmss
    * @author zhengchunfeng
    * @date 2020/1/17 18:45
    * @return java.lang.String
    **/
    public static String getHH_MM_SS(){

        try {
            DateTimeFormatter SimpleDateFormat = DateTimeFormatter.ofPattern(HH_MM_SS);
            return SimpleDateFormat.format(LOCAL_TIME);
        } catch (Exception e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }

    }


    /**
     * @description 获取当前日期YYYY_MM_DD_HH_MM_SS
     * @author zhengchunfeng
     * @date 2020/1/17 18:46
     * @return java.lang.String
     **/
    public static String getYYYY_MM_DD_HH_MM_SS(){

        try {
            DateTimeFormatter SimpleDateFormat = DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS);
            return SimpleDateFormat.format(LOCAL_DATE_TIME);
        } catch (Exception e) {
            e.printStackTrace();
            return StringUtils.EMPTY;
        }

    }



}

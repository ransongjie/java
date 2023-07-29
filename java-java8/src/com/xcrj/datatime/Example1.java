package com.xcrj.datatime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Example1 {
    public static void main(String[] args) {
        test00();
        test01();
        test10();
        test20();
        test21();
        test22();
        test30();
        test31();
        test40();
        test41();
    }

    /**
     * LocalDateTime 获取当前时间
     */
    public static void test00() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
    }

    /**
     * LocalDateTime 创建时间
     */
    public static void test01() {
        // 年 月 日[1,31] 时[0,23] 分[0,59] 秒[0,59] 纳秒[0,999999999]
        LocalDateTime ldt = LocalDateTime.of(2023, 3, 12, 21, 30, 200);
        System.out.println(ldt);
    }

    /**
     * LocalDateTime 格式化 String
     */
    public static void test10() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-dd HH:mm:ss");
        System.out.println(formatter.format(now));
    }

    /**
     * LocalDateTime 转 timestamp 系统时区
     */
    public static void test20() {
        // 本地日期时间
        LocalDateTime now = LocalDateTime.now();
        // 时区ID
        ZoneId zoneId = ZoneId.systemDefault();
        // 时区日期时间
        ZonedDateTime zonedDateTime = now.atZone(zoneId);
        // 时间点
        Instant instant = zonedDateTime.toInstant();
        Long timestamp = instant.toEpochMilli();
        System.out.println(timestamp);
    }

    /**
     * LocalDateTime 转 timestamp 东8区
     */
    public static void test21() {
        LocalDateTime now = LocalDateTime.now();
        Instant instant = now.toInstant(ZoneOffset.of("+8"));
        Long timestamp = instant.toEpochMilli();
        System.out.println(timestamp);
    }

    // timestamp 转 LocalDateTime
    public static void test22() {
        long timestamp = 1673753815000L;
        // 时间点
        Instant instant = Instant.ofEpochMilli(timestamp);
        // 时区ID
        ZoneId zoneId = ZoneId.systemDefault();
        // 时区日期时间
        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        // 本地日期时间
        LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();

        System.out.println(localDateTime);
    }

    // Date 转 LocalDateTime
    public static void test30() {
        Date date = new Date();
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDateTime ldt = instant.atZone(zoneId).toLocalDateTime();

        System.out.println(ldt);
    }

    //LocalDateTime 转 Date
    public static void test31(){
        /*简化*/
        // LocalDateTime localDateTime=LocalDateTime.now();
        // Date date = Date.from(localDateTime.atZone( ZoneId.systemDefault()).toInstant());

        /*详细*/
        LocalDateTime localDateTime = LocalDateTime.now();
        //获取系统默认时区
        ZoneId zoneId = ZoneId.systemDefault();
        //时区的日期和时间
        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        //获取瞬间
        Instant instant=zonedDateTime.toInstant();
        //获取时刻
        Date date = Date.from(instant);

        System.out.println(date);
    }

    //LocalDateTime 加减
    public static void test40(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime after10M=now.plusMinutes(10);
        System.out.println(after10M);
    }

    //LocalDateTime 对比
    public static void test41(){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime ldt = LocalDateTime.of(2021, 11, 12, 21, 30);
        if(ldt.compareTo(now)<0){
            System.out.println("ldt < now");
        }     
    }
}

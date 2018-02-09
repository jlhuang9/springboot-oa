package com.wab.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * @author hcq
 * @create 2018-01-29 下午 4:01
 **/

public class DateUtils {

    public static final Calendar calendar;

    static {
        calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    }

    public static Date getTime() {
        return new Date();
    }


//    public static void main(String[] args) throws InterruptedException {
//        while (true) {
//            TimeUnit.MILLISECONDS.sleep(1000);
//            System.out.println(getTime() );
//        }
//
//    }
}

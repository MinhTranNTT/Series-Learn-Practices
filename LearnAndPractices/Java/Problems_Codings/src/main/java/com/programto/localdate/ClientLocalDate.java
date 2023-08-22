package com.programto.localdate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

public class ClientLocalDate {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Calendar");
        System.out.println(calendar);

        Date time = calendar.getTime();
        System.out.println("Date");
        System.out.println(time);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault());
        System.out.println("LocalDateTime");
        System.out.println(localDateTime);

        LocalDate localDate = LocalDateTime.ofInstant(time.toInstant(), ZoneId.systemDefault()).toLocalDate();
        System.out.println("LocalDate");
        System.out.println(localDate);

        System.out.println("-------------------------------");

        LocalDate localDate1 = getLocalDate(calendar);
        System.out.println("Local Date : " + localDate1);

        LocalTime localTime1 = getLocalTime(calendar);
        System.out.println("Local Time : " + localTime1);

        LocalDateTime localDateTime1 = getLocalDateTime(calendar);
        System.out.println("Local Date Time : " + localDateTime1);
    }

    private static LocalDate getLocalDate(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    private static LocalTime getLocalTime(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault()).toLocalTime();
    }

    private static LocalDateTime getLocalDateTime(Calendar calendar) {
        return LocalDateTime.ofInstant(calendar.toInstant(), ZoneId.systemDefault());
    }
}

## #convert Calendar to LocalDateTime

```
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
```

## #calculate Days between 2 Date JAVA 8 and Older JDK

```
public static void main1(String[] args) {
    LocalDate localDate1 = LocalDate.of(2023, Month.AUGUST, 30);
    Date date1 = convertLocalDateToDate(localDate1);
    long timeDate1 = date1.getTime();
    System.out.println(date1);

    LocalDate localDate2 = LocalDate.of(2023, Month.SEPTEMBER, 2);
    Date date2 = convertLocalDateToDate(localDate2);
    long timeDate2 = date2.getTime();
    System.out.println(date2);

    long diff = 0;
    if (timeDate1 > timeDate2) {
        diff = timeDate1 - timeDate2;
    } else {
        diff = timeDate2 - timeDate1;
    }
    // convert diff
    int days = (int) (diff / (1000 * 60 * 60 * 24));

    System.out.println("Days : " + days);
}

private static Date convertLocalDateToDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
}

public static void main2(String[] args) throws ParseException {
    String formatDate = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(formatDate);

    String date1 = "2023-08-15";
    String date2 = "2023-08-20";

    Date dateDay1 = sdf.parse(date1);
    Date dateDay2 = sdf.parse(date2);

    long timeDate1 = dateDay1.getTime();
    long timeDate2 = dateDay2.getTime();

    long diff = 0;
    if (timeDate1 > timeDate2) {
        diff = timeDate1 - timeDate2;
    } else {
        diff = timeDate2 - timeDate1;
    }

    int days = (int) (diff / (1000 * 60 * 60 * 24));
    System.out.println("Days : " + days);
}

public static void main(String[] args) {
    LocalDate date1 = LocalDate.of(2023, Month.AUGUST, 15);
    LocalDate date2 = LocalDate.of(2023, Month.AUGUST, 20);

    long days1 = ChronoUnit.DAYS.between(date2, date1);

    System.out.println(days1);  // -5

    long days2 = date1.until(date2, ChronoUnit.DAYS);
    System.out.println("Days : " + days2);
}

public static void main(String[] args) {
    String formatDate = "yyyy-MM-dd";
    SimpleDateFormat sdf = new SimpleDateFormat(formatDate);

    String date1 = "2023-08-15";
    String date2 = "2023-08-20";

    LocalDate localDate1 = LocalDate.parse(date1);
    LocalDate localDate2 = LocalDate.parse(date2);

    long days1 = ChronoUnit.DAYS.between(localDate1, localDate2);
    long days2 = localDate1.until(localDate2, ChronoUnit.DAYS);

    System.out.println("Days : " + days1);
    System.out.println("Days : " + days2);
}
```

#
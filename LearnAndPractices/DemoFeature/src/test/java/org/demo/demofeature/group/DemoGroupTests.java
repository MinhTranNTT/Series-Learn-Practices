package org.demo.demofeature.group;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class DemoGroupTests {

    private List<TestVO> getData() {
        return Arrays.asList(
                new TestVO("A", "1"),
                new TestVO("A", "4"),
                new TestVO("A", "6"),
                new TestVO("C", "1"),
                new TestVO("A", "2"),
                new TestVO("B", "2"),
                new TestVO("B", "1")
        );
    }

    private void printData(List<TestVO> data) {
        for (TestVO item : data) {
            System.out.println(item);
        }
    }

    @Test
    public void testGroupingBy() {
        List<TestVO> data = getData();
        // List<TestVO> sortedList = data.stream().sorted(
        //             Comparator.comparing(TestVO::getValue).thenComparing(TestVO::getName))
        //         .collect(Collectors.toList());
        // printData(sortedList);

        LinkedHashMap<String, List<TestVO>> mapData = data.stream().sorted(
                Comparator.comparing(TestVO::getValue)
                        .thenComparing(TestVO::getName)
        ).collect(Collectors.groupingBy(TestVO::getName, LinkedHashMap::new, Collectors.toList()));
        List<TestVO> result = new ArrayList<>();
        mapData.forEach((k, v) -> {
            result.addAll(v.stream().limit(3).toList());
        });
        printData(result);

        // System.out.println(mapData.values().stream().flatMap(List::stream).toList());

    }

    @Test
    public void testGetKeyValueMap() {
        Map<String, String> maps = new HashMap<>();
        maps.put("1", "Apple");
        maps.put("2", "Tomato");
        maps.put("3", "Watermelon");

        maps.forEach((k, v) -> {
            System.out.println(k + " - " + v);
        });
        System.out.println("-------------------------");
        maps.keySet().forEach(System.out::println);
        System.out.println("-------------------------");
        if (maps.keySet() != null) {
            Iterator<String> iterator = maps.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                System.out.println(key);
            }
        }
        System.out.println("-------------------------");
        maps.values().forEach(System.out::println);
        System.out.println("-------------------------");
        maps.entrySet().forEach(entry -> {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        });

    }

    @Test
    public void testSumPropertiesInList() {
        List<Integer> data = List.of(1,2,3,4);
        // Integer reduce = data.stream().reduce(0, (v1, v2) -> v1 + v2);
        Integer reduce = data.stream().reduce(0, Integer::sum);
        System.out.println(reduce);
    }

    @Test
    public void testGetDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.add(Calendar.DATE, 0);
        String today = dateFormat.format(calendar.getTime());
        System.out.println(today + " 00:00:00"); // Bắt đầu ngày
        System.out.println(today + " 23:59:59"); // Kết thúc ngày

        // Thiết lập thứ Hai là ngày đầu tuần
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        String monday = dateFormat.format(calendar.getTime()) + " 00:00:00"; // Bắt đầu tuần
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        String sunday = dateFormat.format(calendar.getTime()) + " 24:00:00"; // Kết thúc tuần
        System.out.println(monday);
        System.out.println(sunday);

        // Lấy thời gian bắt đầu và kết thúc của tháng hiện tại
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        String firstDayOfMonth = dateFormat.format(calendar.getTime()) + " 00:00:00"; // Ngày đầu tháng
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        String lastDayOfMonth = dateFormat.format(calendar.getTime()) + " 24:00:00"; // Ngày cuối tháng
        System.out.println(firstDayOfMonth);
        System.out.println(lastDayOfMonth);

        // Chuyển đổi giữa LocalDateTime và String
        LocalDateTime localDateTime = LocalDateTime.now();
        String time = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDateTime);
        System.out.println("test: " + time);
    }

    @Test
    public void testStringExample() {
        // String[] arr = {"hello","world","hi"};
        // StringBuilder stringBuilder = new StringBuilder("[");
        // for (String s : arr){
        //     stringBuilder.append(s);
        //     stringBuilder.append(",");
        // }
        // String str = stringBuilder.substring(0,stringBuilder.length() - 1);
        // System.out.println("str = " + str);
        // System.out.println("======================");

        String[] arr = {"hello","world","hi"};
        //分隔符 起始符号 结束符号
        StringJoiner sj = new StringJoiner(",","[","]");
        for (String s : arr){
            sj.add(s);
        }
        System.out.println(sj.toString());
    }

    @Test
    public void testStreamLazyEvaluation() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        List<String> collect = names.stream()
                .filter(name -> {
                    System.out.println("Lọc: " + name);
                    return name.length() > 5;
                })
                .map(name -> {
                    System.out.println("Ánh xạ: " + name);
                    return name.toUpperCase();
                })
                .toList();
        System.out.println("collect = " + collect);
    }

    

}

@Data
@AllArgsConstructor
class TestVO {
    private String name;
    private String value;
}

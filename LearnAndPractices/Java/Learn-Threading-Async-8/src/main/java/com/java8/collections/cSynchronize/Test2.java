package com.java8.collections.cSynchronize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args) {
        // Khởi tạo List<TimeRate> và Map<String, Object>
        List<TimeRate> timeRates = Arrays.asList(new TimeRate(10, 0), new TimeRate(10, 10), new TimeRate(10, 20));
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("limit", new AtomicInteger(0));
        dataMap.put("offset", new AtomicInteger(0));

        // Sử dụng CompletableFuture để chạy luồng không đồng bộ
        List<CompletableFuture<Void>> futures = timeRates.stream()
                .map(timeRate -> CompletableFuture.runAsync(() -> {
                    // Gọi hàm queryDatabase() với limit và offset từ AtomicInteger
                    queryDatabase(dataMap, timeRate);
                }))
                .collect(Collectors.toList());

        // Chờ cho tất cả các CompletableFuture hoàn thành
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private static void queryDatabase(Map<String, Object> dataMap, TimeRate timeRate) {
        // Sử dụng AtomicInteger để đảm bảo tính đồng bộ
        AtomicInteger limit = (AtomicInteger) dataMap.get("limit");
        AtomicInteger offset = (AtomicInteger) dataMap.get("offset");

        // Lấy giá trị limit và offset từ timeRate
        int limitValue = timeRate.getLimit();
        int offsetValue = timeRate.getOffset();

        // Sử dụng synchronized để đồng bộ hóa việc cập nhật giá trị
        synchronized (dataMap) {
            limit.set(limitValue);
            offset.set(offsetValue);
        }

        // Thực hiện query xuống database với limit và offset từ AtomicInteger
        System.out.println("Querying database with limit = " + limit + ", offset = " + offset);
        // ...
        // Code thực hiện query xuống database
    }

    static class TimeRate {
        private int limit;
        private int offset;

        public TimeRate(int limit, int offset) {
            this.limit = limit;
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public int getOffset() {
            return offset;
        }
    }
}

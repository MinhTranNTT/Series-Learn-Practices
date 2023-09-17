package com.parallelstepbystepcn.clientcn02.future02;

import com.parallelstepbystepcn.clientcn02.PriceResult;

public class ComparePriceDemo03 {
    public static void main01(String[] args) {
        ComparePriceService service = new ComparePriceService();

        // 方案三测试：使用 CompletableFuture 进一步增强并行
        long start = System.currentTimeMillis();
        PriceResult priceResult = service.getCheapestPlatformPrice3("iPhone");
        long end = System.currentTimeMillis();

        double costTime = (end - start) / 1000.0;
        System.out.printf("cost %.2f second \n",costTime);

        System.out.println("priceResult = " + priceResult);
    }
}

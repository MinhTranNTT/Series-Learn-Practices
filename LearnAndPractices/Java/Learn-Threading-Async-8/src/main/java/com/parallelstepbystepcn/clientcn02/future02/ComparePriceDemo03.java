package com.parallelstepbystepcn.clientcn02.future02;

import com.parallelstepbystepcn.clientcn02.PriceResult;

public class ComparePriceDemo03 {
    public static void main(String[] args) {    // 1.13s
        ComparePriceService service = new ComparePriceService();

        // Option 3 test: Use CompletableFuture to further enhance parallelism
        long start = System.currentTimeMillis();
        PriceResult priceResult = service.getCheapestPlatformPrice3("iPhone");
        long end = System.currentTimeMillis();

        double costTime = (end - start) / 1000.0;
        System.out.printf("cost %.2f second \n",costTime);

        System.out.println("priceResult = " + priceResult);
    }
}

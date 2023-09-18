package com.parallelstepbystepcn.clientcn02.future;

import com.parallelstepbystepcn.clientcn02.PriceResult;

public class ComparePriceDemo02 {
    public static void main(String[] args) {    // 2.24 s
        ComparePriceService service = new ComparePriceService();
        // Option 2 test: Use Future+thread pool to enhance parallelism
        long start = System.currentTimeMillis();
        PriceResult priceResult = service.getCheapestPlatformPrice2("iPhone");
        long end = System.currentTimeMillis();

        double costTime = (end - start) / 1000.0;
        System.out.printf("cost %.2f second \n",costTime);

        System.out.println("priceResult = " + priceResult);
    }
}

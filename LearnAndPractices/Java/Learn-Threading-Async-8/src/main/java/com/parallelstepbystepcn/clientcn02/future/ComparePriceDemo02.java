package com.parallelstepbystepcn.clientcn02.future;

import com.parallelstepbystepcn.clientcn02.PriceResult;

public class ComparePriceDemo02 {
    public static void main(String[] args) {
        ComparePriceService service = new ComparePriceService();
        // 方案二测试：使用Future+线程池增强并行
        long start = System.currentTimeMillis();
        PriceResult priceResult = service.getCheapestPlatformPrice2("iPhone");
        long end = System.currentTimeMillis();

        double costTime = (end - start) / 1000.0;
        System.out.printf("cost %.2f second \n",costTime);

        System.out.println("priceResult = " + priceResult);
    }
}

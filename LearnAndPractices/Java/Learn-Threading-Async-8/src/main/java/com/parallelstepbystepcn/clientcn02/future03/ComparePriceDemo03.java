package com.parallelstepbystepcn.clientcn02.future03;

import com.parallelstepbystepcn.clientcn02.PriceResult;

import java.util.Arrays;
import java.util.List;

public class ComparePriceDemo03 {
    public static void main(String[] args) {
        ComparePriceService service = new ComparePriceService();
        // Test to compare the prices of different colors of the same product (iPhone 14) on one platform
        long start = System.currentTimeMillis();
        List<String> products = Arrays.asList("iPhone14Black", "iPhone14White", "iPhone14Pink");
        PriceResult priceResult = service.batchComparePrice(products);
        long end = System.currentTimeMillis();
        System.out.println("priceResult = " + priceResult);

        double costTime = (end - start) / 1000.0;
        System.out.printf("cost %.2f second \n",costTime);
    }
}

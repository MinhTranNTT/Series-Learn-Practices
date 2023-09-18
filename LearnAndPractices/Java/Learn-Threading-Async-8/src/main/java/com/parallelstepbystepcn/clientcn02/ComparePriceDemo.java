package com.parallelstepbystepcn.clientcn02;

public class ComparePriceDemo {
    public static void main(String[] args) {
        ComparePriceService service = new ComparePriceService();

        long start = System.currentTimeMillis();
        PriceResult priceResult = service.getCheapestPlatformPrice("iPhone");
        long end = System.currentTimeMillis();

        double costTime = (end - start) / 1000.0;
        System.out.printf("cost %.2f second \n",costTime);

        System.out.println("priceResult = " + priceResult);
    }
}

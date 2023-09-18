package com.parallelstepbystepcn.clientcn02;

public class HttpRequest {
    // get price
    public static PriceResult getTaoBaoPrice(String productName) {
        Common02.printThreadLog("Get on Taobao " + productName + " price ");
        mockCostTimeOperation();

        PriceResult priceResult = new PriceResult("Taobao");
        priceResult.setPrice(5199);
        Common02.printThreadLog("Get on Taobao " + productName + " price finish ：5199");
        return priceResult;
    }
    // get discount
    public static int getTaoBaoDiscount(String productName) {
        Common02.printThreadLog("Get on Taobao " + productName + " discount");
        mockCostTimeOperation();
        Common02.printThreadLog("Get on Taobao " + productName + " discount finish ：-200");
        return 200;
    }

    // get JD product
    public static PriceResult getJDongPrice(String productName) {
        Common02.printThreadLog("Get JD.com " + productName + " price ");
        mockCostTimeOperation();

        PriceResult priceResult = new PriceResult("Jingdong");
        priceResult.setPrice(5299);
        Common02.printThreadLog("Get JD.com " + productName + " price finish ：5299");
        return priceResult;
    }
    // get discount product
    public static int getJDongDiscount(String productName) {
        Common02.printThreadLog("Get JD.com " + productName + " discount");
        mockCostTimeOperation();
        Common02.printThreadLog("Get JD.com " + productName + " discount finish ：-150");
        return 150;
    }


    // get price PDD
    public static PriceResult getPDDPrice(String productName) {
        Common02.printThreadLog("Get on Pinduoduo " + productName + " price ");
        mockCostTimeOperation();

        PriceResult priceResult = new PriceResult("Pinduoduo");
        priceResult.setPrice(5399);
        Common02.printThreadLog("Get on Pinduoduo " + productName + " price finish ：5399");
        return priceResult;
    }
    // get discount PDD
    public static int getPDDDiscount(String productName) {
        Common02.printThreadLog("Get on Pinduoduo " + productName + " discount");
        mockCostTimeOperation();
        Common02.printThreadLog("Get on Pinduoduo " + productName + " discount finish ：-5300");
        return 5300;
    }

    // many times
    private static void mockCostTimeOperation() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

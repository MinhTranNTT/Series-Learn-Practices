package com.parallelstepbystepcn.clientcn02;

public class HttpRequest {
    // 获取指定商品的淘宝价
    public static PriceResult getTaoBaoPrice(String productName) {
        Common02.printThreadLog("获取淘宝上" + productName + "价格");
        mockCostTimeOperation();

        PriceResult priceResult = new PriceResult("淘宝");
        priceResult.setPrice(5199);
        Common02.printThreadLog("获取淘宝上" + productName + "价格完成：5199");
        return priceResult;
    }
    // 获取指定商品的淘宝优惠
    public static int getTaoBaoDiscount(String productName) {
        Common02.printThreadLog("获取淘宝上" + productName + "优惠");
        mockCostTimeOperation();
        Common02.printThreadLog("获取淘宝上" + productName + "优惠完成：-200");
        return 200;
    }

    // 获取指定商品的JD价
    public static PriceResult getJDongPrice(String productName) {
        Common02.printThreadLog("获取京东上" + productName + "价格");
        mockCostTimeOperation();

        PriceResult priceResult = new PriceResult("京东");
        priceResult.setPrice(5299);
        Common02.printThreadLog("获取京东上" + productName + "价格完成：5299");
        return priceResult;
    }
    // 获取指定商品的JD优惠
    public static int getJDongDiscount(String productName) {
        Common02.printThreadLog("获取京东上" + productName + "优惠");
        mockCostTimeOperation();
        Common02.printThreadLog("获取京东上" + productName + "优惠完成：-150");
        return 150;
    }


    // 获取指定商品的拼多多价
    public static PriceResult getPDDPrice(String productName) {
        Common02.printThreadLog("获取拼多多上" + productName + "价格");
        mockCostTimeOperation();

        PriceResult priceResult = new PriceResult("拼多多");
        priceResult.setPrice(5399);
        Common02.printThreadLog("获取拼多多上" + productName + "价格完成：5399");
        return priceResult;
    }
    // 获取指定商品的拼多多优惠
    public static int getPDDDiscount(String productName) {
        Common02.printThreadLog("获取拼多多上" + productName + "优惠");
        mockCostTimeOperation();
        Common02.printThreadLog("获取拼多多上" + productName + "优惠完成：-5300");
        return 5300;
    }

    // 模拟耗时的操作
    private static void mockCostTimeOperation() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

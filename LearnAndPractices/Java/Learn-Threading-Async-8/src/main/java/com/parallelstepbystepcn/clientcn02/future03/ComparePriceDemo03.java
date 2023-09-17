package com.parallelstepbystepcn.clientcn02.future03;

import com.parallelstepbystepcn.clientcn02.PriceResult;

import java.util.Arrays;
import java.util.List;

public class ComparePriceDemo03 {
    public static void main(String[] args) {
        ComparePriceService service = new ComparePriceService();
        // 测试在一个平台比较同款产品(iPhone14)不同色系的价格
        List<String> products = Arrays.asList("iPhone14黑色", "iPhone14白色", "iPhone14玫瑰红");
        PriceResult priceResult = service.batchComparePrice(products);
        System.out.println("priceResult = " + priceResult);
    }
}

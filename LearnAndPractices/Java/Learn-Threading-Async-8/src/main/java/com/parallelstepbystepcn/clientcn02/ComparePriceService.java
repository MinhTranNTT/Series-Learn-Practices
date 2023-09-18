package com.parallelstepbystepcn.clientcn02;

import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Stream;

public class ComparePriceService {
    // Option 1: Serial operation of product price comparison
    public PriceResult getCheapestPlatformPrice(String productName) {
        PriceResult priceResult;
        int discount;

        // Get prices and discounts from Taobao platform
        priceResult = HttpRequest.getTaoBaoPrice(productName); // 1s
        discount = HttpRequest.getTaoBaoDiscount(productName); // 1s
        PriceResult taoBaoPriceResult = this.computeRealPrice(priceResult, discount);

        // Get prices and offers on JD platform
        priceResult = HttpRequest.getJDongPrice(productName); // 1s
        discount = HttpRequest.getJDongDiscount(productName); // 1s
        PriceResult jDongPriceResult = this.computeRealPrice(priceResult, discount);

        // Get prices and offers on the Pinduoduo platform
        priceResult = HttpRequest.getPDDPrice(productName); // 1s
        discount = HttpRequest.getPDDDiscount(productName); // 1s
        PriceResult pddPriceResult = this.computeRealPrice(priceResult, discount);

        Stream<PriceResult> stream = Stream.of(taoBaoPriceResult, jDongPriceResult, pddPriceResult);
        Optional<PriceResult> minOpt = stream.min(Comparator.comparing(PriceResult::getRealPrice));
        return minOpt.get();
    }


    // Calculate the final price of the product = platform price - discount price
    public PriceResult computeRealPrice(PriceResult priceResult,int discount) {
        priceResult.setRealPrice(priceResult.getPrice() - discount);
        priceResult.setDiscount(discount);
        Common02.printThreadLog(priceResult.getPlatform() + "Final price calculation completed:" + priceResult.getRealPrice());
        return priceResult;
    }
}

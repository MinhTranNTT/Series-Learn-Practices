package com.parallelstepbystepcn.clientcn02.future;

import com.parallelstepbystepcn.clientcn02.Common02;
import com.parallelstepbystepcn.clientcn02.HttpRequest;
import com.parallelstepbystepcn.clientcn02.PriceResult;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Stream;

public class ComparePriceService {
    // Solution 2: Use Future+thread pool to enhance parallelism
    public PriceResult getCheapestPlatformPrice2(String productName) {
        // Thread Pool
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Get prices and offers from Taobao platform
        Future<PriceResult> taoBaoFuture = executor.submit(() -> {
            PriceResult priceResult = HttpRequest.getTaoBaoPrice(productName);
            int discount = HttpRequest.getTaoBaoDiscount(productName);
            return this.computeRealPrice(priceResult, discount);
        });

        // Get prices and offers on JD platform
        Future<PriceResult> jDongFuture = executor.submit(() -> {
            PriceResult priceResult = HttpRequest.getJDongPrice(productName);
            int discount = HttpRequest.getJDongDiscount(productName);
            return this.computeRealPrice(priceResult, discount);
        });

        // Get prices and offers on the Pinduoduo platform
        Future<PriceResult> pddFuture = executor.submit(() -> {
            PriceResult priceResult = HttpRequest.getPDDPrice(productName);
            int discount = HttpRequest.getPDDDiscount(productName);
            return this.computeRealPrice(priceResult, discount);
        });

        // Compare and calculate the cheapest platforms and prices
        return Stream.of(taoBaoFuture, jDongFuture, pddFuture)
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .min(Comparator.comparing(PriceResult::getRealPrice))
                .get();
    }

    // Calculate the final price of the product = platform price - discount price
    public PriceResult computeRealPrice(PriceResult priceResult,int discount) {
        priceResult.setRealPrice(priceResult.getPrice() - discount);
        priceResult.setDiscount(discount);
        Common02.printThreadLog(priceResult.getPlatform() + " Final price calculation completed: " + priceResult.getRealPrice());
        return priceResult;
    }
}

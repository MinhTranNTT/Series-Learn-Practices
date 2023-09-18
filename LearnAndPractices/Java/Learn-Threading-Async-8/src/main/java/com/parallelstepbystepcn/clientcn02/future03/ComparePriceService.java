package com.parallelstepbystepcn.clientcn02.future03;

import com.parallelstepbystepcn.clientcn02.Common02;
import com.parallelstepbystepcn.clientcn02.HttpRequest;
import com.parallelstepbystepcn.clientcn02.PriceResult;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ComparePriceService {
    public PriceResult batchComparePrice(List<String> products) {
        // Traverse each product, start an asynchronous task based on the product to obtain the final price, and then collect it into the List collection
        List<CompletableFuture<PriceResult>> completableFutures = products.stream()
                .map(product -> {
                    return CompletableFuture
                            .supplyAsync(() -> HttpRequest.getTaoBaoPrice(product))
                            .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequest.getTaoBaoDiscount(product)), this::computeRealPrice);
                }).collect(Collectors.toList());

        // Sort and compare the final prices of multiple products to obtain the minimum value
        return completableFutures
                .stream()
                .map(CompletableFuture::join)
                .sorted(Comparator.comparing(PriceResult::getRealPrice))
                .findFirst()
                .get();
    }

    public PriceResult computeRealPrice(PriceResult priceResult, int discount) {
        priceResult.setRealPrice(priceResult.getPrice() - discount);
        priceResult.setDiscount(discount);
        Common02.printThreadLog(priceResult.getPlatform() + " Final price calculation completed: " + priceResult.getRealPrice());
        return priceResult;
    }
}

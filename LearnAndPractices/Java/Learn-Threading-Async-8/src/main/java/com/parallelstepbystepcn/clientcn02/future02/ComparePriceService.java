package com.parallelstepbystepcn.clientcn02.future02;

import com.parallelstepbystepcn.clientcn02.Common02;
import com.parallelstepbystepcn.clientcn02.HttpRequest;
import com.parallelstepbystepcn.clientcn02.PriceResult;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ComparePriceService {
    // Option 3: Use CompletableFuture to further enhance parallelism
    public PriceResult getCheapestPlatformPrice3(String productName) {
        // Get prices and offers from Taobao platform
        CompletableFuture<PriceResult> taoBaoCF = CompletableFuture
                .supplyAsync(() -> HttpRequest.getTaoBaoPrice(productName))
                .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequest.getTaoBaoDiscount(productName)), this::computeRealPrice);

        // Get prices and offers from JDong platform
        CompletableFuture<PriceResult> jDongCF = CompletableFuture
                .supplyAsync(() -> HttpRequest.getJDongPrice(productName))
                .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequest.getJDongDiscount(productName)), this::computeRealPrice);

        // Get prices and offers from Pindoudou platform
        CompletableFuture<PriceResult> pddCF = CompletableFuture
                .supplyAsync(() -> HttpRequest.getPDDPrice(productName))
                .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequest.getPDDDiscount(productName)), this::computeRealPrice);

        return Stream.of(taoBaoCF,jDongCF,pddCF)
                .map(CompletableFuture::join)
                .min(Comparator.comparing(PriceResult::getRealPrice))
                .get();

    }

    public PriceResult computeRealPrice(PriceResult priceResult, int discount) {
        priceResult.setRealPrice(priceResult.getPrice() - discount);
        priceResult.setDiscount(discount);
        Common02.printThreadLog(priceResult.getPlatform() + " Final price calculation completed: " + priceResult.getRealPrice());
        return priceResult;
    }
}

package com.parallelstepbystepcn.clientcn02.future02;

import com.parallelstepbystepcn.clientcn02.HttpRequest;
import com.parallelstepbystepcn.clientcn02.PriceResult;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

public class ComparePriceService {
    // 方案三：使用 CompletableFuture 进一步增强并行
    public PriceResult getCheapestPlatformPrice3(String productName) {
        // 获取淘宝平台的价格和优惠
        CompletableFuture<PriceResult> taoBaoCF = CompletableFuture
                .supplyAsync(() -> HttpRequest.getTaoBaoPrice(productName))
                .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequest.getTaoBaoDiscount(productName)), this::computeRealPrice);

        // 获取京东平台的价格和优惠
        CompletableFuture<PriceResult> jDongCF = CompletableFuture
                .supplyAsync(() -> HttpRequest.getJDongPrice(productName))
                .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequest.getJDongDiscount(productName)), this::computeRealPrice);
        // 获取拼多多平台的价格和优惠
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
        //LogUtils.printLog(priceResult.getPlatform() + "最终价格计算完成:" + priceResult.getRealPrice());
        return priceResult;
    }
}

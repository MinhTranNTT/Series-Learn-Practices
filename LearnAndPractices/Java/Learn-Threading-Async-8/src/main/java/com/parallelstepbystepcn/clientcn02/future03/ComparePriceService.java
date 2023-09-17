package com.parallelstepbystepcn.clientcn02.future03;

import com.parallelstepbystepcn.clientcn02.HttpRequest;
import com.parallelstepbystepcn.clientcn02.PriceResult;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class ComparePriceService {
    public PriceResult batchComparePrice(List<String> products) {
        // 遍历每个商品，根据商品开启异步任务获取最终价，然后归集到List集合
        List<CompletableFuture<PriceResult>> completableFutures = products.stream()
                .map(product -> {
                    return CompletableFuture
                            .supplyAsync(() -> HttpRequest.getTaoBaoPrice(product))
                            .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequest.getTaoBaoDiscount(product)), this::computeRealPrice);
                }).collect(Collectors.toList());

        // 把多个商品的最终价进行排序比较获取最小值
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
        //LogUtils.printLog(priceResult.getPlatform() + "最终价格计算完成:" + priceResult.getRealPrice());
        return priceResult;
    }
}

package com.parallelstepbystepcn.clientcn02;

import lombok.Data;

@Data
public class PriceResult {
    private int price;			// 平台价格
    private int discount;		// 折扣
    private int realPrice;		// 最终价
    private String platform;	// 商品平台

    public PriceResult(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "PriceResult{" +
                "平台=" + platform +
                ", 价格=" + price +
                ", 折扣=" + discount +
                ", 最终价=" + realPrice +
                '}';
    }
}

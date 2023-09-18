package com.parallelstepbystepcn.clientcn02;

import lombok.Data;

@Data
public class PriceResult {
    private int price;			// base price
    private int discount;		// discount
    private int realPrice;		// sale price
    private String platform;	// platform

    public PriceResult(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return "PriceResult{" +
                "Platform=" + platform +
                ", BasePrice=" + price +
                ", Discount=" + discount +
                ", SalePrice=" + realPrice +
                '}';
    }
}

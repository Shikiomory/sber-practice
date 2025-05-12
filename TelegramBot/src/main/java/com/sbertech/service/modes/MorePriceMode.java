package com.sbertech.service.modes;

public class MorePriceMode implements PriceMode {
    @Override
    public boolean isChange(float oldPrice, float newPrice) {
        return oldPrice < newPrice;
    }
}

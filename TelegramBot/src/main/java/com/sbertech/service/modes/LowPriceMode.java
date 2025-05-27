package com.sbertech.service.modes;

public class LowPriceMode implements PriceMode{
    @Override
    public boolean isChange(float oldPrice, float newPrice) {
        return oldPrice > newPrice;
    }
}

package com.sbertech.service.modes;

public interface PriceMode {
    boolean isChange(float oldPrice, float newPrice);
}

package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 100;

    public FixPriceProduct(UUID id, String name) {
        super(id, name, FIXED_PRICE);
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя продукта не может быть пустым.");
        }
    }


    @Override
    public int getPrice() {
        return FIXED_PRICE;
    }

    @Override
    public int getFinalPrice() {
        return FIXED_PRICE;
    }


    @Override
    public String toString() {
        return getName() + ": фиксированная цена " + FIXED_PRICE + " руб.";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
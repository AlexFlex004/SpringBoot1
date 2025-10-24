package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private final int price;

    public SimpleProduct(UUID id, String name, int price) {
        super(id, name, price);
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя продукта не может быть пустым.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Цена продукта должна быть больше 0.");
        }
        this.price = price;
    }


    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getFinalPrice() {
        return price;
    }


    @Override
    public String toString() {
        return getName() + ": " + price + " руб.";
    }
}
package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private final int defaultPrice;
    private final int discount;

    public DiscountedProduct(UUID id, String name, int defaultPrice, int discount) {
        super(id, name, defaultPrice);

        if (defaultPrice <= 0) {
            throw new IllegalArgumentException("Базовая цена продукта должна быть больше 0.");
        }
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0 до 100%.");
        }

        this.defaultPrice = defaultPrice;
        this.discount = discount;
    }

    @Override
    public int getPrice() {
        return defaultPrice;
    }

    @Override
    public int getFinalPrice() {
        return defaultPrice - (defaultPrice * discount / 100);
    }




    @Override
    public String toString() {
        return getName() +
                ": базовая цена = " + defaultPrice +
                ", скидка = " + discount + "%" +
                ", цена со скидкой = " + getFinalPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}
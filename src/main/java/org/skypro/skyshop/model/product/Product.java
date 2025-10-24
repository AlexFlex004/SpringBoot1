package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private final String name;
    private final int basePrice;

    public Product(UUID id, String name, int basePrice) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя продукта не может быть пустым.");
        }
        this.name = name;
        this.basePrice = basePrice;
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    @JsonIgnore
    public String getSearchTerm() {
        return name;
    }

    public String getName() {
        return name;
    }


    // Каждому продукту по умолчанию указываем тип "Продукт"
    @Override
    @JsonIgnore
    public String getContentType() {
        return "Продукт";
    }

    public abstract int getPrice();

    public abstract int getFinalPrice();


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Product{name='" + name + "'}";
    }

    public boolean isSpecial() {
        return false;
    }
}
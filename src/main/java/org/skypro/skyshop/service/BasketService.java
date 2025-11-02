package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class BasketService {

    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    //ДОБАВИТЬ
    public void addProductToBasket(UUID id) {
        var productOptional = storageService.getProductById(id);
        if (productOptional.isEmpty()) {
            throw new IllegalArgumentException("Продукт с id " + id + " не найден");
        }
        productBasket.addProduct(id);
    }

    //ПОЛУЧИТЬ
    public UserBasket getUserBasket() {
        var basketItems = productBasket.getProducts().entrySet().stream()
                .map(entry -> new BasketItem(
                        storageService.getProductById(entry.getKey()).orElseThrow(),
                        entry.getValue()
                ))
                .collect(Collectors.toList());

        return new UserBasket(basketItems);
    }
}

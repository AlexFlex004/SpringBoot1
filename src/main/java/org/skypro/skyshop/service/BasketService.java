package org.skypro.skyshop.service;

import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        Optional<Product> productOptional = storageService.getProductById(id);
        if (productOptional.isEmpty()) {
            throw new NoSuchProductException("Товар с id " + id + " не найден");
        }
        productBasket.addProduct(id);
    }

    //ПОЛУЧИТЬ
    public UserBasket getUserBasket() {
        List<BasketItem> basketItems = productBasket.getProducts().entrySet().stream()
                .map(entry -> new BasketItem(
                        storageService.getProductById(entry.getKey()).orElseThrow(),
                        entry.getValue()
                ))
                .collect(Collectors.toList());

        return new UserBasket(basketItems);
    }
}

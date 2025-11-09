package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.exception.NoSuchProductException;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasketServiceTest {

    private ProductBasket productBasket;
    private StorageService storageService;
    private BasketService basketService;

    @BeforeEach
    void setUp() {
        productBasket = Mockito.mock(ProductBasket.class);
        storageService = Mockito.mock(StorageService.class);
        basketService = new BasketService(productBasket, storageService);
    }

    @Test
    void testAddProductThrowsExceptionWhenProductNotFound() {
        UUID id = UUID.randomUUID();
        when(storageService.getProductById(id)).thenReturn(Optional.empty());
        assertThrows(NoSuchProductException.class, () -> basketService.addProductToBasket(id));

        verify(productBasket, never()).addProduct(any());
    }

    @Test
    void testAddProductCallsAddProductWhenExists() {
        UUID id = UUID.randomUUID();
        Product product = new SimpleProduct(id, "Фонарь", 600);
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(id);

        verify(productBasket, times(1)).addProduct(id);
    }

    @Test
    void testGetUserBasketReturnsEmptyWhenBasketEmpty() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        assertTrue(userBasket.getItems().isEmpty());
    }

    @Test
    void testGetUserBasketReturnsCorrectBasket() {
        UUID id = UUID.randomUUID();
        Product product = new SimpleProduct(id, "Лодка", 50000);

        Map<UUID, Integer> products = new HashMap<>();
        products.put(id, 2);

        when(productBasket.getProducts()).thenReturn(products);
        when(storageService.getProductById(id)).thenReturn(Optional.of(product));

        UserBasket basket = basketService.getUserBasket();

        assertEquals(1, basket.getItems().size());
        assertEquals("Лодка", basket.getItems().get(0).getProduct().getName());
        assertEquals(2, basket.getItems().get(0).getQuantity());
    }
}

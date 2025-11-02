package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {

    private final Map<UUID, Product> products;
    private final Map<UUID, Article> articles;
    public Collection<Product> getAllProducts;
    public Collection<Article> getAllArticles;
    private final Map<UUID, Product> availableProducts;
    private final Map<UUID, Article> availableArticles;

    public StorageService () {
        availableProducts = new HashMap<>();
        availableArticles = new HashMap<>();
        this.products = new HashMap<>();
        this.articles = new HashMap<>();
        initializeTestData();
        initializeData();
        
    }

    private void initializeTestData() {

        Product p1 = new SimpleProduct(UUID.randomUUID(), "Спиннинг рыболовный", 10000);
        Product p2 = new DiscountedProduct(UUID.randomUUID(), "Фонарь", 600, 10);
        Product p3 = new SimpleProduct(UUID.randomUUID(), "Лодка", 50000);

        products.put(p1.getId(), p1);
        products.put(p2.getId(), p2);
        products.put(p3.getId(), p3);


        Article a1 = new Article(UUID.randomUUID(), "Новая статья о технологиях", new String[]{"tech", "gadgets"});
        Article a2 = new Article(UUID.randomUUID(), "Как выбрать смартфон", new String[]{"guide", "mobile"});
        Article a3 = new Article(UUID.randomUUID(), "Будущее ноутбуков", new String[]{"laptop", "future"});

        articles.put(a1.getId(), a1);
        articles.put(a2.getId(), a2);
        articles.put(a3.getId(), a3);
    }

    private void initializeData() {

        Product p1 = new SimpleProduct(UUID.randomUUID(), "Спиннинг рыболовный", 10000);
        Product p2 = new DiscountedProduct(UUID.randomUUID(), "Фонарь", 600, 10);
        Product p3 = new SimpleProduct(UUID.randomUUID(), "Лодка", 50000);

        availableProducts.put(p1.getId(), p1);
        availableProducts.put(p2.getId(), p2);
        availableProducts.put(p3.getId(), p3);


        Article a1 = new Article(UUID.randomUUID(), "Новая статья о технологиях", new String[]{"tech", "gadgets"});
        Article a2 = new Article(UUID.randomUUID(), "Как выбрать смартфон", new String[]{"guide", "mobile"});
        Article a3 = new Article(UUID.randomUUID(), "Будущее ноутбуков", new String[]{"laptop", "future"});

        availableArticles.put(a1.getId(), a1);
        availableArticles.put(a2.getId(), a2);
        availableArticles.put(a3.getId(), a3);
    }

    public Collection<Product> getAllProducts() {
        return availableProducts.values();
    }

    public Collection<Article> getAllArticles() {
        return availableArticles.values();
    }

    public Collection<Searchable> getAllSearchables() {
        Collection<Searchable> all = new java.util.ArrayList<>();
        all.addAll(availableProducts.values());
        all.addAll(availableArticles.values());
        return all;
    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts.get(id));
    }

}

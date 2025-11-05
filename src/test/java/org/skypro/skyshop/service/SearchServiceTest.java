package org.skypro.skyshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SearchServiceTest {

    private StorageService storageService;
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        storageService = Mockito.mock(StorageService.class);
        searchService = new SearchService(storageService);
    }

    @Test
    void testSearchReturnsEmptyWhenNoObjects() {
        Collection<Searchable> emptyList = new ArrayList<>();
        when(storageService.getAllSearchables()).thenReturn(emptyList);

        List<SearchResult> result = (List<SearchResult>) searchService.search("test");

        assertTrue(result.isEmpty());
        verify(storageService, times(1)).getAllSearchables();
    }

    @Test
    void testSearchReturnsEmptyWhenNoMatches() {
        List<Searchable> list = new ArrayList<>();
        list.add(new SimpleProduct(UUID.randomUUID(), "Фонарь", 1000));
        list.add(new Article(UUID.randomUUID(), "Как выбрать удочку", new String[]{"рыбалка"}));

        when(storageService.getAllSearchables()).thenReturn(list);

        List<SearchResult> result = (List<SearchResult>) searchService.search("телефон");

        assertTrue(result.isEmpty());
    }

    @Test
    void testSearchReturnsMatches() {
        List<Searchable> list = new ArrayList<>();
        list.add(new SimpleProduct(UUID.randomUUID(), "Телефон", 20000));
        list.add(new SimpleProduct(UUID.randomUUID(), "Ноутбук", 50000));

        when(storageService.getAllSearchables()).thenReturn(list);

        List<SearchResult> result = (List<SearchResult>) searchService.search("телефон");

        assertEquals(1, result.size());
        assertEquals("Телефон", result.get(0).getName());
    }
}
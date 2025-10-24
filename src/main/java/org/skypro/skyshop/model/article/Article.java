package org.skypro.skyshop.model.article;

import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.model.tag.Taggable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable, Taggable {
    private final UUID id;
    private final String title;
    private final String[] tags;

    public Article(UUID id, String title, String[] tags) {
        this.title = title;
        this.tags = tags;
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getSearchTerm() {
        return title;
    }

    @Override
    public String[] getTags() {
        return tags;
    }

    // equals и hashCode только по названию
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    @Override
    public String getContentType() {
        return "Статья";
    }
}

package com.sansarch.bookstore_catalog_service.domain.book.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Builder
@Data
public class Book {

    private Long id;
    private String title;
    private String author;
    private BigDecimal price;
    private Long stockAvailability;

    public void changeTitle(String title) {
        this.title = title;
    }

    public void changeAuthor(String author) {
        this.author = author;
    }

    public void changePrice(BigDecimal price) {
        this.price = price;
    }

    public void changeStockAvailability(Long stockAvailability) {
        if (stockAvailability < 0) {
            throw new IllegalArgumentException("Stock availability cannot be negative");
        }
        this.stockAvailability = stockAvailability;
    }
}

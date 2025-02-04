package com.sansarch.bookstore_catalog_service.domain.book.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void shouldChangeTitleCorrectly() {
        Book book = Book
                .builder()
                .title("Old title")
                .build();

        assertEquals("Old title", book.getTitle());

        book.changeTitle("New Title");

        assertEquals("New Title", book.getTitle());
    }

    @Test
    void shouldChangeAuthorCorrectly() {
        Book book = Book
                .builder()
                .author("Old author")
                .build();

        assertEquals("Old author", book.getAuthor());

        book.changeAuthor("New author");

        assertEquals("New author", book.getAuthor());
    }

    @Test
    void shouldChangePriceCorrectly() {
        Book book = Book
                .builder()
                .price(BigDecimal.valueOf(10))
                .build();

        assertEquals(BigDecimal.valueOf(10), book.getPrice());

        book.changePrice(BigDecimal.valueOf(20));

        assertEquals(BigDecimal.valueOf(20), book.getPrice());
    }

    @Test
    void shouldChangeStockAvailabilityCorrectly() {
        Book book = Book
                .builder()
                .stockAvailability(10)
                .build();

        assertEquals(10, book.getStockAvailability());

        book.changeStockAvailability(20);

        assertEquals(20, book.getStockAvailability());
    }

    @Test
    void shouldThrowExceptionWhenChangingStockAvailabilityToNegative() {
        Book book = Book
                .builder()
                .stockAvailability(10)
                .build();

        assertEquals(10, book.getStockAvailability());

        assertThrows(IllegalArgumentException.class, () -> book.changeStockAvailability(-1));
    }
}
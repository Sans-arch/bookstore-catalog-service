package com.sansarch.bookstore_catalog_service.domain.common.messaging;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EventTest {

    @Test
    void shouldCreateEventCorrectly() {
        Event event = new Event(1L);
        assertEquals(1L, event.getId());
    }

    @Test
    void shouldCreateEventCorrectlyWithNullId() {
        Event event = new Event(null);
        assertNull(event.getId());
    }
}
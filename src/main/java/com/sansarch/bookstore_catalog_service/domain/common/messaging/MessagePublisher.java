package com.sansarch.bookstore_catalog_service.domain.common.messaging;


public interface MessagePublisher {
    void publish(Event event, String exchange);
}

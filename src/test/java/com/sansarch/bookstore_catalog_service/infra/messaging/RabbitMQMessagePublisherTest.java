package com.sansarch.bookstore_catalog_service.infra.messaging;

import com.sansarch.bookstore_catalog_service.domain.common.messaging.Event;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RabbitMQMessagePublisherTest {

    @Mock private RabbitTemplate rabbitTemplate;
    @InjectMocks private RabbitMQMessagePublisher publisher;


    @Test
    void shouldPublishMessageToExchange() {
        Event event = mock(Event.class);
        when(event.getId()).thenReturn(123L);
        String exchange = "test-exchange";

        publisher.publish(event, exchange);

        verify(rabbitTemplate).convertAndSend(exchange, "", event);
    }
}
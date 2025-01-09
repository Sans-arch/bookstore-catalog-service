package com.sansarch.bookstore_catalog_service.infra.messaging;

import com.sansarch.bookstore_catalog_service.domain.common.messaging.MessagePublisher;
import com.sansarch.bookstore_catalog_service.domain.common.messaging.Event;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Slf4j
@Component
public class RabbitMQMessagePublisher implements MessagePublisher {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(Event event, String exchange) {
        rabbitTemplate.convertAndSend(
                exchange,
                "",
                event);
        log.info("Message published to exchange: {}, event: {}", exchange, event.getId());
    }
}

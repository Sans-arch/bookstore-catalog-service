package com.sansarch.bookstore_catalog_service.infra.book.dto;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CheckStockBookAvailabilityDto {
    private Long bookId;
    private Boolean available;
    private Integer stock;
}

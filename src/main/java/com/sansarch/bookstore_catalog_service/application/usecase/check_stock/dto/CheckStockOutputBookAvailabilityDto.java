package com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CheckStockOutputBookAvailabilityDto {
    private Long bookId;
    private Boolean isAvailable;
    private Integer requestedQuantity;
    private Integer stock;
}

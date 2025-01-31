package com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CheckStockOutputDto {
    private Boolean allAvailable;
    private List<CheckStockOutputBookAvailabilityDto> booksAvailability;
}

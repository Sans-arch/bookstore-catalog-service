package com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto;

import com.sansarch.bookstore_catalog_service.infra.book.dto.CheckStockBookAvailabilityDto;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CheckStockOutputDto {
    private Boolean allAvailable;
    private List<CheckStockBookAvailabilityDto> booksAvailability;
}

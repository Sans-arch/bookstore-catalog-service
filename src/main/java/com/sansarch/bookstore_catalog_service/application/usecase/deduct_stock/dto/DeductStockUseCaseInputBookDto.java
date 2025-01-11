package com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeductStockUseCaseInputBookDto {
    private Long bookId;
    private Integer quantity;
}

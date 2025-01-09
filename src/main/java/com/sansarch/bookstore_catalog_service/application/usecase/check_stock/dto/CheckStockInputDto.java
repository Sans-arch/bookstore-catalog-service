package com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CheckStockInputDto {
    private Long bookId;
    private Integer quantity;
}

package com.sansarch.bookstore_catalog_service.infra.book.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class StockDeductionInputDto {
    private Long bookId;
    private Integer quantity;
}

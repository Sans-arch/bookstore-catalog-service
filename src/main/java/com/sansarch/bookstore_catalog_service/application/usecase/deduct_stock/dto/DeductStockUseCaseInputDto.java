package com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock.dto;

import com.sansarch.bookstore_catalog_service.infra.book.dto.StockDeductionInputDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class DeductStockUseCaseInputDto {
    private List<StockDeductionInputDto> booksToBeDeducted;
}

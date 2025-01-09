package com.sansarch.bookstore_catalog_service.infra.http.controller;

import com.sansarch.bookstore_catalog_service.application.service.CatalogService;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockOutputDto;
import com.sansarch.bookstore_catalog_service.infra.book.dto.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/catalog")
public class CatalogController {

    private CatalogService catalogService;

    @PostMapping
    public ResponseEntity<CreateBookOutputDto> addBook(@RequestBody CreateBookInputDto input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogService.addBookToCatalog(input));
    }

    @GetMapping
    public ResponseEntity<List<ListBooksOutputDto>> listAllBooks() {
        return ResponseEntity.ok(catalogService.listAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindBookOutputDto> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(catalogService.findBook(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditBookOutputDto> editBook(@PathVariable Long id, @RequestBody EditBookInputDto input) {
        return ResponseEntity.ok(catalogService.editBook(id, input));
    }

    @PutMapping("/stock")
    public ResponseEntity<Void> deductStock(@RequestBody List<StockDeductionInputDto> input) {
        catalogService.deductStock(input);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/stock/check-stock-availability")
    public ResponseEntity<CheckStockOutputDto> checkStockAvailability(@RequestBody List<CheckStockInputDto> input) {
        return ResponseEntity.ok(catalogService.checkStockAvailability(input));
    }
}

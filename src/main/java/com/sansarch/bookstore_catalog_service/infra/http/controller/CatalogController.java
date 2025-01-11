package com.sansarch.bookstore_catalog_service.infra.http.controller;

import com.sansarch.bookstore_catalog_service.application.service.CatalogService;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.dto.ListAllBooksUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookOutputDto;
import com.sansarch.bookstore_catalog_service.infra.book.dto.EditBookInputDto;
import com.sansarch.bookstore_catalog_service.infra.book.dto.EditBookOutputDto;
import com.sansarch.bookstore_catalog_service.infra.book.dto.FindBookOutputDto;
import com.sansarch.bookstore_catalog_service.infra.book.dto.StockDeductionInputDto;
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
    public ResponseEntity<RegisterBookOutputDto> addBook(@RequestBody RegisterBookInputDto input) {
        return ResponseEntity.status(HttpStatus.CREATED).body(catalogService.addBookToCatalog(input));
    }

    @GetMapping
    public ResponseEntity<ListAllBooksUseCaseOutputDto> listAllBooks() {
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

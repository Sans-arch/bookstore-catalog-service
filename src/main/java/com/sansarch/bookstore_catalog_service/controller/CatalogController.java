package com.sansarch.bookstore_catalog_service.controller;

import com.sansarch.bookstore_catalog_service.dto.CreateBookInputDto;
import com.sansarch.bookstore_catalog_service.dto.CreateBookOutputDto;
import com.sansarch.bookstore_catalog_service.service.CatalogService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CatalogController {

    private CatalogService catalogService;

    @PostMapping
    public CreateBookOutputDto addBook(CreateBookInputDto input) {
        return catalogService.addBookToCatalog(input);
    }

//    // list all books
//    @GetMapping
//    public void listAllBooks() {
//
//    }
//
//    // get details of specific book
//    @GetMapping("/${id}")
//    public void getBook(@PathVariable Long id) {
//
//    }
//
//    // update book details - put
//    @PutMapping("/${id}")
//    public void updateBook(@PathVariable Long id) {
//
//    }
}

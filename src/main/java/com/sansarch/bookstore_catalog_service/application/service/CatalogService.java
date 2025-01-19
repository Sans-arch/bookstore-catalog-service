package com.sansarch.bookstore_catalog_service.application.service;

import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.CheckStockUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.check_stock.dto.CheckStockOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock.DeductStockUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock.dto.DeductStockUseCaseInputBookDto;
import com.sansarch.bookstore_catalog_service.application.usecase.deduct_stock.dto.DeductStockUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.EditBookUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseInputBookDto;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.find_book.FindBookUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.find_book.dto.FindBookUseCaseInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.find_book.dto.FindBookUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.ListAllBooksUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.dto.ListAllBooksUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.RegisterBookUseCase;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookOutputDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CatalogService {

    private CheckStockUseCase checkStockUseCase;
    private RegisterBookUseCase registerBookUseCase;
    private ListAllBooksUseCase listAllBooksUseCase;
    private FindBookUseCase findBookUseCase;
    private EditBookUseCase editBookUseCase;
    private DeductStockUseCase deductStockUseCase;

    public RegisterBookOutputDto addBookToCatalog(RegisterBookInputDto input) {
        return registerBookUseCase.execute(input);
    }

    public ListAllBooksUseCaseOutputDto listAllBooks() {
        return listAllBooksUseCase.execute(null);
    }

    public FindBookUseCaseOutputDto findBook(Long id) {
        return findBookUseCase.execute(new FindBookUseCaseInputDto(id));
    }

    public EditBookUseCaseOutputDto editBook(Long id, EditBookUseCaseInputBookDto payload) {
        return editBookUseCase.execute(new EditBookUseCaseInputDto(id, payload));
    }

    public void deductStock(List<DeductStockUseCaseInputBookDto> input) {
        deductStockUseCase.execute(new DeductStockUseCaseInputDto(input));
    }

    public CheckStockOutputDto checkStockAvailability(List<CheckStockInputDto> items) {
        return checkStockUseCase.execute(items);
    }
}

package com.sansarch.bookstore_catalog_service.application.mapper;

import com.sansarch.bookstore_catalog_service.application.usecase.edit_book.dto.EditBookUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.find_book.dto.FindBookUseCaseOutputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.list_all_books.dto.ListAllBooksUseCaseOutputBookDto;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookInputDto;
import com.sansarch.bookstore_catalog_service.application.usecase.registrate_book.dto.RegisterBookOutputDto;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.infra.book.repository.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    Book createBookInputDtoToBookEntity(RegisterBookInputDto dto);

    RegisterBookOutputDto bookEntityToCreateBookOutputDto(Book book);

    List<ListAllBooksUseCaseOutputBookDto> bookListToListAllBooksOutputDtoList(List<Book> books);

    FindBookUseCaseOutputDto bookEntityToFindBookOutputDto(Book book);

    EditBookUseCaseOutputDto bookEntityToEditBookOutputDto(Book book);

    Book bookModelToBookEntity(BookModel bookModel);

    BookModel bookEntityToBookModel(Book book);
}

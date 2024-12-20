package com.sansarch.bookstore_catalog_service.application.mapper;

import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.infra.book.dto.*;
import com.sansarch.bookstore_catalog_service.infra.book.repository.model.BookModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    Book createBookInputDtoToBookEntity(CreateBookInputDto dto);

    CreateBookOutputDto bookModelToCreateBookOutputDto(BookModel bookModel);

    List<ListBooksOutputDto> bookModelListToListBooksOutputDtoList(List<BookModel> bookModels);

    FindBookOutputDto bookModelToFindBookOutputDto(BookModel bookModel);

    FindBookOutputDto bookEntityToFindBookOutputDto(Book book);

    EditBookOutputDto bookModelToEditBookOutputDto(BookModel bookModel);

    Book bookModelToBookEntity(BookModel bookModel);

    BookModel bookEntityToBookModel(Book book);
}

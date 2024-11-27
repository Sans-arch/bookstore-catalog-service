package com.sansarch.bookstore_catalog_service.mapper;

import com.sansarch.bookstore_catalog_service.dto.*;
import com.sansarch.bookstore_catalog_service.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    Book createBookInputDtoToBookEntity(CreateBookInputDto dto);

    CreateBookOutputDto bookEntityToCreateBookOutputDto(Book book);

    List<ListBooksOutputDto> bookEntityListToListBooksOutputDtoList(List<Book> books);

    FindBookOutputDto bookEntityToFindBookOutputDto(Book book);

    EditBookOutputDto bookEntityToEditBookOutputDto(Book book);
}

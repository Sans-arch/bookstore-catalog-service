package com.sansarch.bookstore_catalog_service.mapper;

import com.sansarch.bookstore_catalog_service.dto.CreateBookInputDto;
import com.sansarch.bookstore_catalog_service.dto.CreateBookOutputDto;
import com.sansarch.bookstore_catalog_service.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "title", target = "title")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "stockAvailability", target = "stockAvailability")
    Book createBookInputDtoToBookEntity(CreateBookInputDto dto);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "author", target = "author")
    @Mapping(source = "stockAvailability", target = "stockAvailability")
    CreateBookOutputDto bookEntityToCreateBookOutputDto(Book book);
}

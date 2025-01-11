package com.sansarch.bookstore_catalog_service.infra.book.repository;

import com.sansarch.bookstore_catalog_service.application.mapper.BookMapper;
import com.sansarch.bookstore_catalog_service.domain.book.entity.Book;
import com.sansarch.bookstore_catalog_service.domain.book.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaBookRepository implements BookRepository {

    private final SpringDataBookRepository springDataRepository;

    @Override
    public Optional<Book> findById(Long id) {
        return springDataRepository.findById(id).map(BookMapper.INSTANCE::bookModelToBookEntity);
    }

    @Override
    public List<Book> findAll() {
        return springDataRepository.findAll().stream().
                map(BookMapper.INSTANCE::bookModelToBookEntity).toList();
    }

    @Override
    public void save(Book book) {
        springDataRepository.save(BookMapper.INSTANCE.bookEntityToBookModel(book));
    }
}

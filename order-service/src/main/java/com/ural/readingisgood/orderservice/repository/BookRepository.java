package com.ural.readingisgood.orderservice.repository;

import com.ural.readingisgood.orderservice.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {


    boolean existsByNameAndAuthor(String name, String author);

    BookEntity findByName(String name);


}
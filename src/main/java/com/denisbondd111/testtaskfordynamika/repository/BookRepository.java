package com.denisbondd111.testtaskfordynamika.repository;

import com.denisbondd111.testtaskfordynamika.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

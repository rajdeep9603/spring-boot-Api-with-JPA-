package com.rest.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rest.models.Book;
@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
	public Book findById(int Id);

}

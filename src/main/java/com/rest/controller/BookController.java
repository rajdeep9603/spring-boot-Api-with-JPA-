package com.rest.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.bookservices.BookServices;
import com.rest.models.Book;

@RestController
public class BookController {

	@Autowired
	private BookServices bookServices;

	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {

//		make single obj of class : 
//		Book book = new Book();
//		book.setId(123);
//		book.setTitle("java book");
//		book.setAuthor("by rajdeep");

		List<Book> bookslist =  this.bookServices.getBooks();
		if(bookslist.size()<=0) { 
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
//		return ResponseEntity.of(Optional.of(bookslist));
		return ResponseEntity.status(HttpStatus.CREATED).body(bookslist);

	}

	@GetMapping("/books/{id}")
	public ResponseEntity<Book> singlebook(@PathVariable("id") int id) {
		Book book =  this.bookServices.getBookById(id);
		if(book == null){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	@PostMapping("/books")
		public ResponseEntity<Book> addBookNew(@RequestBody Book book){
			Book book2 = null;
			try {
				 book2 =  this.bookServices.addBook(book);
				System.out.println("new added book "+book2);
				return ResponseEntity.of(Optional.of(book2));
			}catch(Exception e){
//				to do handle exception case here;
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				
			}
//			return book2;
		}
	
//	delete book handler : by self 
//	@DeleteMapping("/books/{bookid}")
//	public Book deletebook(@PathVariable("bookid") int id){
//		Book deletedbook = null;
//		deletedbook = this.bookServices.deleteBookById(id);
//		return deletedbook;
//		
//	}
	
	
//	delete boook handler by tutorials :-->
	@DeleteMapping("/books/{bookidnew}")
	public ResponseEntity<Void> newBookDeleteId(@PathVariable("bookidnew") int id){
		try {
			
			this.bookServices.deleteBookAnotherMethod(id);
//			return ResponseEntity.ok().build(); or 
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); 

		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
	
	
//	update book handler 
	@PutMapping("/books/{bookid}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookid") int bookid){
		try {
			this.bookServices.updateBook(book,bookid);
			return ResponseEntity.of(Optional.of(book));
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
}

package com.rest.bookservices;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.rest.dao.BookRepository;
import com.rest.models.Book;

@Component
@Service
public class BookServices {
//	private static List<Book> booklist = new ArrayList<>();
//	static {
//		booklist.add(new Book(111, "new1", "raj1"));
//		booklist.add(new Book(112, "new2", "raj2"));
//		booklist.add(new Book(113, "new3", "raj3"));
//	}
	@Autowired
	private BookRepository bookRepository;

//  get all the books : coustom method is made by me with sattus code handling :static
//	public List<Book> getbooks() {
//		return booklist;
//	}
	
	public List<Book> getBooks(){
		List<Book> list = (List<Book>)this.bookRepository.findAll();
		return list;
	}

//  get single book by single id  : 
	public Book getBookById(int id) {
		Book booknew = null;
		try {
			
//			static
//			booknew = booklist.stream().filter(e -> e.getId() == id).findFirst().get();

			
//			from db 
			booknew = this.bookRepository.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return booknew;
	}

//  adding a book : static

	public Book addBook(Book b) {
		
//		static
//		booklist.add(b);
	
//		db 
		Book bb = this.bookRepository.save(b);
		return bb;
	}

//  delete book handler : by self:static
//	public Book deleteBookById(int id) {
//		Book bookdelete = null;
//		bookdelete = booklist.stream().filter(e -> e.getId() == id).findFirst().get();
//		if (bookdelete.getId() == id) {
//			booklist.remove(bookdelete);
//			return bookdelete;
//		} else {
//			return bookdelete;
//		}
//	}

//  delete book by tutorials vidio :-->static

	public void deleteBookAnotherMethod(int id) {
//		static
	
//		booklist = booklist.stream().filter(b -> b.getId() != id).collect(Collectors.toList());

//	db
		this.bookRepository.deleteById(id);
	}

//  update boook by id : static
//	public void updateBookById(Book updateval, int id) {
//		booklist = booklist.stream().map(eachbook -> {
//			if (eachbook.getId() == id) {
//				eachbook.setTitle(updateval.getTitle());
//				eachbook.setAuthor(updateval.getAuthor());
//			}
//			return eachbook;
//		}).collect(Collectors.toList());
//	}

	
//	db
	public void updateBook(Book book,int bookId){
		book.setId(bookId);
		bookRepository.save(book);
	}
}

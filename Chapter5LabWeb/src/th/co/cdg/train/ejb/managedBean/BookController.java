package th.co.cdg.train.ejb.managedBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import th.co.cdg.train.ejb.entity.Book;
import th.co.cdg.train.ejb.session.BookLocal;
import th.co.cdg.train.ejb.session.BookNativeQueryLocal;
import th.co.cdg.train.ejb.session.BookQueryLocal;
import th.co.cdg.train.ejb.session.BookTranLocal;

@ManagedBean
public class BookController {

	@EJB
	private BookLocal bookLocal;
	@EJB
	private BookQueryLocal bookQueryLocal;
	@EJB
	private BookNativeQueryLocal bookNativeQueryLocal;
	@EJB
	private BookTranLocal bookTranLocal;
	
	private Book book;
	private List<Book> bookResults;

	public BookController() {
		book = book == null ? new Book() : book;
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public List<Book> getBookResults() {
		return bookResults;
	}

	public void setBookResults(List<Book> bookResults) {
		this.bookResults = bookResults;
	}

	
	public void findBook() {
		Book book = bookQueryLocal.findBook(getBook().getId());
//		Book book = bookNativeQueryLocal.findBook(getBook().getId());
		
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		setBookResults(books);
	}
	
	public void queryBookById() {
		Book book = bookQueryLocal.queryBookById(getBook().getId());
//		Book book = bookNativeQueryLocal.queryBookById(getBook().getId());
		
		List<Book> books = new ArrayList<Book>();
		books.add(book);
		setBookResults(books);
	}
	
	public void queryBookByCondition() {
		List<Book> books = bookQueryLocal.queryBookByCondition(getBook());
//		List<Book> books = bookNativeQueryLocal.queryBookByCondition(getBook());
		setBookResults(books);
	}
	
	public void insertBook() {
		bookLocal.insertBook(book);
	}
	
	public void updateBook() {
		bookLocal.updateBook(book);
	}
	
	public void updateBookByAttached() {
		bookLocal.updateBookByAttached(book);
	}
	
	public void updateBookNoMerge() {
		bookLocal.updateBookNoMerge(book);
	}
	
	public void deleteBook() {
		bookLocal.deleteBook(book.getId());
	}
	
	public void deleteBookByBookEntity() {
		bookLocal.deleteBookByBookEntity(book);
	}
	
	public void testRequire() {
		try {
			bookTranLocal.testRequire();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testRequiresNew1() {
		try {
			bookTranLocal.testRequiresNew1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testMadatory() {
		try {
			Book book1 = new Book();
			book1.setId(3001);
			book1.setTitle("TransactionAttributeType.MANDATORY");
			book1.setTitle("EJB");
			book1.setPublicationYear(2017);
			book1.setUnitPrice(BigDecimal.valueOf(100));
			bookTranLocal.testMadatory(book1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testNever1() {
		try {
			bookTranLocal.testNever1();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testSupport() {
		try {
			bookTranLocal.testSupport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testNotSupport() {
		try {
			bookTranLocal.testNotSupport();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

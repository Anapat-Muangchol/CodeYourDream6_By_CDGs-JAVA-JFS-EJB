package th.co.cdg.train.ejb.managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import th.co.cdg.train.ejb.entity.Book;
import th.co.cdg.train.ejb.session.BookLocal;

@ManagedBean
public class BookController {
	
	@EJB
	private BookLocal bookLocal;
	
	private Book book;
	
	public BookController() {
		book = book == null ? new Book() : book;
	}
	
	public Book getBook() {
		return book;
	}
	
	public void insertBookToLocal(){
		bookLocal.insertBook(book);
	}
	
	public void updateBookToLocal(){
		bookLocal.updateBook(book);
	}
	
	public void deleteBookToLocal(){
		bookLocal.deleteBook(book);
	}
	
	public void selectBookById(){
		book = bookLocal.selectBookById(book);
	}
}

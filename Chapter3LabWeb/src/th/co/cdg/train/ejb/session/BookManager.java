package th.co.cdg.train.ejb.session;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdg.train.ejb.entity.Book;

/**
 * Session Bean implementation class BookManager
 */
@Stateless
@LocalBean
public class BookManager implements BookRemote, BookLocal {
	
	@PersistenceContext(unitName = "trainlab")
	private EntityManager em;
	
	@Override
	public void insertBook(Book book) {
		em.persist(book);
	}

	@Override
	public void updateBook(Book book) {
		Book attachedBook = em.find(Book.class, book.getId());
		attachedBook.setTitle(book.getTitle());
		attachedBook.setAuthor(book.getAuthor());
		attachedBook.setPublicationYear(book.getPublicationYear());
		attachedBook.setUnitPrice(book.getUnitPrice());
		em.merge(attachedBook); //There is no such command.

//		em.merge(book);
	}

	@Override
	public void deleteBook(Book book) {
//		em.remove(em.merge(book));
		em.remove(em.find(Book.class, book.getId()));
	}

	@Override
	public Book selectBookById(Book book) {
		return em.find(Book.class, book.getId());
	}

}

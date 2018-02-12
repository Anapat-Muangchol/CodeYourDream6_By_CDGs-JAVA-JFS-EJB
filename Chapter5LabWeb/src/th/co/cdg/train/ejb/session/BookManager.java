package th.co.cdg.train.ejb.session;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		em.merge(book);
	}

	@Override
	public void updateBookByAttached(Book book) {
		Book attachedBook = em.find(Book.class, book.getId());
		attachedBook.setTitle(book.getTitle());
		attachedBook.setAuthor(book.getAuthor());
		attachedBook.setPublicationYear(book.getPublicationYear());
		attachedBook.setUnitPrice(book.getUnitPrice());
		em.merge(attachedBook);
	}

	@Override
	public void updateBookNoMerge(Book book) {
	}

	@Override
	public void deleteBook(Integer id) {
		em.remove(em.find(Book.class, id));
	}

	@Override
	public void deleteBookByBookEntity(Book book) {
		em.remove(em.merge(book));
	}

}

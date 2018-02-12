package th.co.cdg.train.ejb.session;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdg.train.ejb.entity.Book;

/**
 * Session Bean implementation class BookQueryManager
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class BookQueryManager implements BookQueryLocal {

	@PersistenceContext(unitName = "trainlab")
	private EntityManager em;

	@Override
	public Book findBook(Integer id) {
		return em.find(Book.class, id);
	}

	@Override
	public Book queryBookById(Integer id) {
		final String jpql = "select b from Book b where b.id = :bookId";
		
		Query query = em.createQuery(jpql);
		query.setParameter("bookId", id);
		
		return (Book)query.getSingleResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> queryBookByCondition(Book book) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append("select b from Book b where 1=1 ");
		
		if ( book.getTitle() != null && !"".equals(book.getTitle()) ) {
			jpql.append(" and b.title = :bookTitle ");
		}
		
		if ( book.getPublicationYear() != null ) {
			jpql.append(" and b.publicationYear = :bookPublicationYear");
		}
		
		Query query = em.createQuery(jpql.toString());
		
		if ( book.getTitle() != null && !"".equals(book.getTitle()) ) {
			query.setParameter("bookTitle", book.getTitle());
		}
		
		if ( book.getPublicationYear() != null ) {
			query.setParameter("bookPublicationYear", book.getPublicationYear());
		}
		
		return (List<Book>)query.getResultList();
	}

}

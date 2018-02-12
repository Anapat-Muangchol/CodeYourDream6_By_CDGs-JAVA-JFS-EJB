package th.co.cdg.train.ejb.session;

import java.math.BigDecimal;
import java.util.ArrayList;
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
 * Session Bean implementation class BookNativeQueryManager
 */
@Stateless
@LocalBean
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class BookNativeQueryManager implements BookNativeQueryLocal {

	@PersistenceContext(unitName = "trainlab")
	private EntityManager em;

	@Override
	public Book findBook(Integer id) {
		
		final String sql = "select * from book where id = :bookId";
		
		Query query = em.createNativeQuery(sql, Book.class);
		query.setParameter("bookId", id);
		
		return (Book)query.getSingleResult();
	}

	@Override
	public Book queryBookById(Integer id) {
		
		final String sql = "select * from book where id = :bookId";
		
		Query query = em.createNativeQuery(sql);
		query.setParameter("bookId", id);
		
		Object o = query.getSingleResult();
		
		if ( o != null ) {
			Object[] resultObject = (Object[])o;
			Book book = new Book();
			book.setId((Integer)resultObject[0]);
			book.setTitle((String)resultObject[1]);
			book.setAuthor((String)resultObject[2]);
			book.setPublicationYear((Integer)resultObject[3]);
			book.setUnitPrice((BigDecimal)resultObject[4]);
			
			return book;
		}
		
		return null;
	}

	@Override
	public List<Book> queryBookByCondition(Book book) {
		final StringBuilder jpql = new StringBuilder();
		jpql.append("select b.* from book b where 1=1 ");
		
		if ( book.getTitle() != null && !"".equals(book.getTitle()) ) {
			jpql.append(" and b.title = :bookTitle ");
		}
		
		if ( book.getPublicationYear() != null ) {
			jpql.append(" and b.publication_year = :bookPublicationYear");
		}
		
		Query query = em.createNativeQuery(jpql.toString());
		
		if ( book.getTitle() != null && !"".equals(book.getTitle()) ) {
			query.setParameter("bookTitle", book.getTitle());
		}
		
		if ( book.getPublicationYear() != null ) {
			query.setParameter("bookPublicationYear", book.getPublicationYear());
		}

		Object o = query.getResultList();
		
		if ( o != null ) {
			
			List<Book> books = new ArrayList<Book>();
			if ( o instanceof Object[]) {
				Object[] resultObject = (Object[])o;
				Book b = new Book();
				b.setId((Integer)resultObject[0]);
				b.setTitle((String)resultObject[1]);
				b.setAuthor((String)resultObject[2]);
				b.setPublicationYear((Integer)resultObject[3]);
				b.setUnitPrice((BigDecimal)resultObject[4]);
				
				books.add(b);
			}else {
				List<Object[]> resultObjects = (List<Object[]>)o;
				for (Object[] resultObject : resultObjects) {
					Book b = new Book();
					b.setId((Integer)resultObject[0]);
					b.setTitle((String)resultObject[1]);
					b.setAuthor((String)resultObject[2]);
					b.setPublicationYear((Integer)resultObject[3]);
					b.setUnitPrice((BigDecimal)resultObject[4]);
					
					books.add(b);
				}
			}
			
			
			return books;
		}
		
		return null;
	}

}

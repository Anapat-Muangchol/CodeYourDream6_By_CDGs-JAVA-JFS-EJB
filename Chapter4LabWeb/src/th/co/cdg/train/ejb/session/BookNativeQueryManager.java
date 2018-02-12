package th.co.cdg.train.ejb.session;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdg.train.ejb.entity.Book;

/**
 * Session Bean implementation class BookNativeQueryManager
 */
@Stateless
@LocalBean
public class BookNativeQueryManager implements BookNativeQueryLocal {
	
	@PersistenceContext(unitName = "trainlab")
	private EntityManager em;
	
	@Override
	public Book findBook(Integer id) {
		return null;
	}

	@Override
	public Book queryBookById(Integer id) {
		
		final String sql = "select * from Book b where id = :bookId";
		Query query = em.createNativeQuery(sql);
		query.setParameter("bookId", id);
		
		Object o = query.getSingleResult();
		
		if(o != null){
			Object[] resultObject = (Object[]) o;
			Book book = new Book();
			book.setId((Integer) resultObject[0]);
			book.setTitle((String) resultObject[1]);
			book.setAuthor((String) resultObject[2]);
			book.setPublicationYear((Integer) resultObject[3]);
			book.setUnitPrice((BigDecimal) resultObject[4]);
			
			return book;
		}
		
		return null;
	}

	@Override
	public List<Book> queryBookByCondition(Book book) {
		final StringBuilder sql = new StringBuilder();
		sql.append("select * from book b where 1=1");
		
		if(book.getId() != null){
			sql.append(" and b.id = :bookId");
		}
		if(!"".equals(book.getTitle())){
			sql.append(" and b.title like :bookTitle");
		}
		if(!"".equals(book.getAuthor())){
			sql.append(" and b.author like :bookAuthor");
		}
		if(book.getPublicationYear() != null){
			sql.append(" and b.publication_year = :bookPublicationYear");
		}
//		if(book.getUnitPriceStart() != null && book.getUnitPriceEnd() != null){
//			jpql.append(" and b.unitPrice between :bookUnitPriceStart and :bookUnitPriceEnd");
//		}
		
//		Query query = em.createNativeQuery(sql.toString());
		Query query = em.createNativeQuery(sql.toString(), Book.class);
		
		if(book.getId() != null){
			query.setParameter("bookId", book.getId());
		}
		if(!"".equals(book.getTitle())){
			query.setParameter("bookTitle", "%"+book.getTitle()+"%");
		}
		if(!"".equals(book.getAuthor())){
			query.setParameter("bookAuthor", "%"+book.getAuthor()+"%");
		}
		if(book.getPublicationYear() != null){
			query.setParameter("bookPublicationYear", book.getPublicationYear());
		}
//		if(book.getUnitPriceStart() != null && book.getUnitPriceEnd() != null){
//			query.setParameter("bookUnitPriceStart", book.getUnitPriceStart());
//			query.setParameter("bookUnitPriceEnd", book.getUnitPriceEnd());
//		}
		
//		System.out.println(sql);
		
//		if(query instanceof List){
//			
//		}else{
//			
//		}
		
		return (List<Book>) query.getResultList();
		
	}

    
}

package th.co.cdg.train.ejb.session;

import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import th.co.cdg.train.ejb.entity.Book;

/**
 * Session Bean implementation class BookTranManager
 */
@Stateless
@LocalBean
public class BookTranManager implements BookTranLocal {

	@EJB
	private BookLocal bookLocal;
	
	@EJB
	private BookTranLocal bookTranLocal;
	
	@EJB
	private BookQueryLocal bookQueryLocal;
	
	@EJB
	private BookNativeQueryLocal bookNativeQueryLocal;
	
	@Resource
	private SessionContext context;
	
	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void testRequire() throws Exception {
		Book book1 = new Book();
		book1.setId(1001);
		book1.setTitle("TransactionAttributeType.REQUIRED");
		book1.setAuthor("EJB");
		book1.setPublicationYear(2017);
		book1.setUnitPrice(BigDecimal.valueOf(100));
		bookLocal.insertBook(book1);
		
		if ( true ) {
			context.setRollbackOnly();
			throw new Exception("TEST : TransactionAttributeType.REQUIRED");
		}

		Book book2 = new Book();
		book2.setId(1002);
		book2.setTitle("TransactionAttributeType.REQUIRED_2");
		book2.setAuthor("EJB");
		book2.setPublicationYear(2017);
		book2.setUnitPrice(BigDecimal.valueOf(100));
		bookLocal.insertBook(book2);
	}

	@Override
	public void testRequiresNew1() throws Exception {

		Book book1 = new Book();
		book1.setId(2001);
		book1.setTitle("TransactionAttributeType.REQUIRES_NEW");
		book1.setAuthor("EJB");
		book1.setPublicationYear(2017);
		book1.setUnitPrice(BigDecimal.valueOf(100));
		bookTranLocal.testRequiresNew2(book1);
		
		if ( true ) {
			context.setRollbackOnly();
			throw new Exception("TEST : TransactionAttributeType.REQUIRES_NEW_2");
		}
		Book book2 = new Book();
		book1.setId(2002);
		book1.setTitle("TransactionAttributeType.REQUIRES_NEW_2");
		book1.setAuthor("EJB");
		book1.setPublicationYear(2017);
		book1.setUnitPrice(BigDecimal.valueOf(100));
		bookLocal.insertBook(book1);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	public void testRequiresNew2(Book book) {
		bookLocal.insertBook(book);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.MANDATORY)
	public void testMadatory(Book book) {
		bookLocal.insertBook(book);
	}

	@Override
	public void testNever1() {
		Book book1 = new Book();
		book1.setId(4001);
		book1.setTitle("TransactionAttributeType.NEVER");
		book1.setAuthor("EJB");
		book1.setPublicationYear(2017);
		book1.setUnitPrice(BigDecimal.valueOf(100));
		bookTranLocal.testNever2(book1);
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.NEVER)
	public void testNever2(Book book) {
		bookLocal.insertBook(book);
	}

	@Override
	public void testSupport() throws Exception {
		Book book1 = new Book();
		book1.setId(5001);
		book1.setTitle("TransactionAttributeType.SUPPORTS");
		book1.setAuthor("EJB");
		book1.setPublicationYear(2017);
		book1.setUnitPrice(BigDecimal.valueOf(100));
		bookLocal.insertBook(book1);
		
		Book bookSupport = bookQueryLocal.queryBookById(book1.getId());
		if ( bookSupport != null ) {
			System.out.println("bookSupport = " + bookSupport);
			System.out.println("bookSupport.getId() = " + bookSupport.getId());
			context.setRollbackOnly();
			throw new Exception("TEST : TransactionAttributeType.SUPPORTS");
		}

		Book book2 = new Book();
		book2.setId(5002);
		book2.setTitle("TransactionAttributeType.SUPPORTS_2");
		book2.setAuthor("EJB");
		book2.setPublicationYear(2017);
		book2.setUnitPrice(BigDecimal.valueOf(100));
		bookLocal.insertBook(book2);
	}

	@Override
	public void testNotSupport() throws Exception {
		Book book1 = new Book();
		book1.setId(6001);
		book1.setTitle("TransactionAttributeType.NOT_SUPPORTED");
		book1.setAuthor("EJB");
		book1.setPublicationYear(2017);
		book1.setUnitPrice(BigDecimal.valueOf(100));
		bookLocal.insertBook(book1);
		
		Book bookSupport = bookNativeQueryLocal.queryBookById(book1.getId());
		if ( bookSupport == null ) {
			throw new Exception("TEST : TransactionAttributeType.NOT_SUPPORTED");
		}

		Book book2 = new Book();
		book2.setId(6002);
		book2.setTitle("TransactionAttributeType.NOT_SUPPORTED_2");
		book2.setAuthor("EJB");
		book2.setPublicationYear(2017);
		book2.setUnitPrice(BigDecimal.valueOf(100));
		bookLocal.insertBook(book2);
	}


}

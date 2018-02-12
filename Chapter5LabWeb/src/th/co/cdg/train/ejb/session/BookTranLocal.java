package th.co.cdg.train.ejb.session;

import javax.ejb.Local;

import th.co.cdg.train.ejb.entity.Book;

@Local
public interface BookTranLocal {

	public void testRequire() throws Exception;
	
	public void testRequiresNew1() throws Exception;
	
	public void testRequiresNew2(Book book);
	
	public void testMadatory(Book book);
	
	public void testNever1();
	
	public void testNever2(Book book);
	
	public void testSupport() throws Exception;
	
	public void testNotSupport() throws Exception;

}

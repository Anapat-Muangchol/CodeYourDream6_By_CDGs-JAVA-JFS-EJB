package th.co.cdg.train.ejb.session;

import javax.ejb.Remote;

import th.co.cdg.train.ejb.entity.Book;

@Remote
public interface BookRemote {

	public void insertBook(Book book);
	
	public void updateBook(Book book);
	
	public void updateBookByAttached(Book book);
	
	public void updateBookNoMerge(Book book);
	
	public void deleteBook(Integer id);
	
	public void deleteBookByBookEntity(Book book);
	
}



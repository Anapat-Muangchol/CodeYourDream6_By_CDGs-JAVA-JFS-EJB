package th.co.cdg.train.ejb.session;

import java.util.List;

import javax.ejb.Remote;

import th.co.cdg.train.ejb.entity.Book;

@Remote
public interface BookRemote {
	public void insertBook(Book book);
	public void updateBook(Book book);
	public void deleteBook(Book book);
	public Book selectBookById(Book book);
}

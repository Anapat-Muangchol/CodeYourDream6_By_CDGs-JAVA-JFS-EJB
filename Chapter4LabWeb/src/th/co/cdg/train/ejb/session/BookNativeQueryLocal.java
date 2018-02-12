package th.co.cdg.train.ejb.session;

import java.util.List;

import javax.ejb.Local;

import th.co.cdg.train.ejb.entity.Book;

@Local
public interface BookNativeQueryLocal {
	public Book findBook(Integer id);
	public Book queryBookById(Integer id);
	public List<Book> queryBookByCondition(Book book);
}

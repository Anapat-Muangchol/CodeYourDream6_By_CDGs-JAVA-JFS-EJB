package th.co.cdg.train.ejb.test;

import java.math.BigDecimal;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import th.co.cdg.train.ejb.entity.Book;
import th.co.cdg.train.ejb.session.BookRemote;

public class TestLabClient {
	
	private static Context context;

	public static Context getContext() {
		try {

			if ( context != null ) {
				return context;
			}
			Properties jndiProps = new Properties();
			jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
			jndiProps.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			jndiProps.put("jboss.naming.client.ejb.context", true);
			
			jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
//			jndiProps.put(Context.PROVIDER_URL, "http-remoting://10.17.1.247:8080");
			
			jndiProps.put(Context.SECURITY_PRINCIPAL, "tester");
			
			jndiProps.put(Context.SECURITY_CREDENTIALS, "password");
			// create a context passing these properties
			context = new InitialContext(jndiProps);

			return context;
		} catch (Exception e) {
			return null;
		}
	}
	
	private static BookRemote getBookRemote() throws NamingException {
//		System.out.println(BookRemote.class.getName());
		return (BookRemote)getContext().lookup("Chapter3LabWeb/BookManager!" + BookRemote.class.getName());
//		return (HelloRemote)getContext().lookup("Chapter3LabWeb/insertBook!th.co.cdg.train.ejb.chapter1.HelloRemote");
	}

	public static void main(String[] args) {
		testInsertBook();
	}
	
	public static void testInsertBook() {

		try {
			Book book = new Book();
			book.setId(2309);
			book.setTitle("RemoteTest");
			book.setAuthor("anapat");
			book.setPublicationYear(2000);
			book.setUnitPrice(new BigDecimal("999"));
			
			getBookRemote().insertBook(book);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

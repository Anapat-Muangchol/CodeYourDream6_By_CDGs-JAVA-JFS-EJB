package th.co.cdg.train.ejb.client;



import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import th.co.cdg.train.ejb.chapter1.HelloRemote;

public class TestHello {
	
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
			
//			jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			jndiProps.put(Context.PROVIDER_URL, "http-remoting://10.17.1.247:8080");
			
			jndiProps.put(Context.SECURITY_PRINCIPAL, "tester");
			
			jndiProps.put(Context.SECURITY_CREDENTIALS, "password");
			// create a context passing these properties
			context = new InitialContext(jndiProps);

			return context;
		} catch (Exception e) {
			return null;
		}
	}
	
	private static HelloRemote getHelloRemote() throws NamingException {
		return (HelloRemote)getContext().lookup("Chapter1LabWeb/HelloBean!" + HelloRemote.class.getName());
//		return (HelloRemote)getContext().lookup("Chapter1LabWeb/HelloBean!th.co.cdg.train.ejb.chapter1.HelloRemote");
	}

	public static void main(String[] args) {
//		while(true){
//			try {
//			    Thread.sleep(500);                 //1000 milliseconds is one second.
//			} catch(InterruptedException ex) {
//			    Thread.currentThread().interrupt();
//			}
//			testStateless();
//		}
		testStateless();
	}
	
	public static void testStateless() {

		try {
			
			System.out.println(getHelloRemote().hello("Spam"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
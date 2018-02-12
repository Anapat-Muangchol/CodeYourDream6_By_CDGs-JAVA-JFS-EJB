package th.co.cdg.train.ejb.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import th.co.cdg.train.ejb.chapter2.TestStatefulRemote;
import th.co.cdg.train.ejb.chapter2.TestStatelessRemote;

public class TestCompareStatelessStateful {
	
	private static final String TAB_STATELESS1 = "";
	private static final String TAB_STATELESS2 = "\t\t\t";
	private static final String TAB_STATELESS3 = "\t\t\t\t\t\t";

	private static final String TAB_STATEFUL1 = "\t\t\t\t\t\t\t\t\t";
	private static final String TAB_STATEFUL2 = "\t\t\t\t\t\t\t\t\t\t\t\t";
	private static final String TAB_STATEFUL3 = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
	
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
			
			jndiProps.put(Context.SECURITY_PRINCIPAL, "tester");
			
			jndiProps.put(Context.SECURITY_CREDENTIALS, "password");
			// create a context passing these properties
			context = new InitialContext(jndiProps);

			return context;
		} catch (Exception e) {
			return null;
		}
	}
	
	private static TestStatefulRemote getTestStatefulRemote() throws NamingException {
		return (TestStatefulRemote)getContext().lookup("Chapter2LabWeb/TestStatefulBean!" + TestStatefulRemote.class.getName());
	}
	
	private static TestStatelessRemote getTestStatelessRemote() throws NamingException {
		return (TestStatelessRemote)getContext().lookup("Chapter2LabWeb/TestStatelessBean!" + TestStatelessRemote.class.getName());
	}


	public static void main(String[] args) {
		testStateless();
		System.out.println("");
		testStateful();
		
	}
	
	public static void print(String tab, String threadName, TestStatefulRemote remote) {
		print(tab, threadName, remote.getCounter());
	}
	
	public static void print(String tab, String threadName, TestStatelessRemote remote) {
		print(tab, threadName, remote.getCounter());
	}
	
	public static void print(String tab, String threadName, int counter) {
		try {
			Thread.sleep(1000 * 1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(tab + threadName + "=" + counter);
	}
	
	public static void testStateless() {

		try {
			
			new Thread("testStateless1")
			{
			    public void run() {
					try {
						TestStatelessRemote remote = getTestStatelessRemote();
						Thread.sleep(1000 * 3);
						System.out.println(this.getName() + "=" + remote);
						print(TAB_STATELESS1, this.getName(), remote);
						print(TAB_STATELESS1, this.getName(), remote);
						print(TAB_STATELESS1, this.getName(), remote);
						print(TAB_STATELESS1, this.getName(), remote);
						print(TAB_STATELESS1, this.getName(), remote);
						Thread.sleep(1000 * 5);
					} catch (Exception e) {
						e.printStackTrace();
					}
			    }
			}.start();


			new Thread("testStateless2")
			{
			    public void run() {
					try {
						TestStatelessRemote remote = getTestStatelessRemote();
						Thread.sleep(1000 * 3);
						System.out.println(this.getName() + "=" + remote);
						print(TAB_STATELESS2, this.getName(), remote);
						print(TAB_STATELESS2, this.getName(), remote);
						print(TAB_STATELESS2, this.getName(), remote);
						print(TAB_STATELESS2, this.getName(), remote);
						print(TAB_STATELESS2, this.getName(), remote);
						Thread.sleep(1000 * 5);
					} catch (Exception e) {
						e.printStackTrace();
					}
			    }
			}.start();
			
			new Thread("testStateless3")
			{
			    public void run() {
					try {
						Thread.sleep(1000 * 3);
						System.out.println(this.getName() + "=" + getTestStatelessRemote().getCounter());
						print(TAB_STATELESS3, this.getName(), getTestStatelessRemote());
						print(TAB_STATELESS3, this.getName(), getTestStatelessRemote());
						print(TAB_STATELESS3, this.getName(), getTestStatelessRemote());
						print(TAB_STATELESS3, this.getName(), getTestStatelessRemote());
						print(TAB_STATELESS3, this.getName(), getTestStatelessRemote());
						Thread.sleep(1000 * 5);
					} catch (Exception e) {
						e.printStackTrace();
					}
			    }
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void testStateful() {

		try {

			new Thread("testStateful1")
			{
			    public void run() {
					try {
						TestStatefulRemote remote = getTestStatefulRemote();
						Thread.sleep(1000 * 3);
						System.out.println(this.getName() + "=" + remote);
						print(TAB_STATEFUL1, this.getName(), remote);
						print(TAB_STATEFUL1, this.getName(), remote);
						print(TAB_STATEFUL1, this.getName(), remote);
						print(TAB_STATEFUL1, this.getName(), remote);
						print(TAB_STATEFUL1, this.getName(), remote);
						Thread.sleep(1000 * 5);
					} catch (Exception e) {
						e.printStackTrace();
					}
			    }
			}.start();
			
			new Thread("testStateful2")
			{
			    public void run() {
					try {
						TestStatefulRemote remote = getTestStatefulRemote();
						Thread.sleep(1000 * 3);
						System.out.println(this.getName() + "=" + remote);
						print(TAB_STATEFUL2, this.getName(), remote);
						print(TAB_STATEFUL2, this.getName(), remote);
						print(TAB_STATEFUL2, this.getName(), remote);
						print(TAB_STATEFUL2, this.getName(), remote);
						print(TAB_STATEFUL2, this.getName(), remote);
						Thread.sleep(1000 * 5);
					} catch (Exception e) {
						e.printStackTrace();
					}
			    }
			}.start();
			
			new Thread("testStateful3")
			{
			    public void run() {
					try {
						Thread.sleep(1000 * 3);
						print(TAB_STATEFUL3, this.getName(), getTestStatefulRemote());
						print(TAB_STATEFUL3, this.getName(), getTestStatefulRemote());
						print(TAB_STATEFUL3, this.getName(), getTestStatefulRemote());
						print(TAB_STATEFUL3, this.getName(), getTestStatefulRemote());
						print(TAB_STATEFUL3, this.getName(), getTestStatefulRemote());
						Thread.sleep(1000 * 5);
					} catch (Exception e) {
						e.printStackTrace();
					}
			    }
			}.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
}
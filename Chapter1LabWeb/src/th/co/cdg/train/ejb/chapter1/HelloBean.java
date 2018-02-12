package th.co.cdg.train.ejb.chapter1;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class HelloBean
 */
@Stateless
@LocalBean
public class HelloBean implements HelloRemote {

	@Override
	public String hello(String name) {
		// TODO Auto-generated method stub
//		String msg = "Hello from Instructor Session bean, "+name;
		String msg = "I am is real, "+name;
		System.out.println(msg);
		return msg;
	}

}

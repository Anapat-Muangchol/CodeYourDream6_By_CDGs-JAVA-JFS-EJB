package th.co.cdg.train.ejb.chapter2;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

/**
 * Session Bean implementation class TestStatefulBean
 */
@Stateful
@LocalBean
public class TestStatefulBean implements TestStatefulRemote, TestStatefulLocal {

	private int counter = 0;

	@Override
	public int getCounter() {
		System.out.println("TestStatefulBean("+this+"):"+counter);
		return counter++;
	}

}

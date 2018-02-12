package th.co.cdg.train.ejb.managedBean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import th.co.cdg.train.ejb.chapter2.TestStatelessLocal;

@ManagedBean
public class IndexController {
	
	@EJB
	private TestStatelessLocal testStatelessLocal;
	
	private Integer counter;

	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}
	
	public void getCounterByLocal(){
		setCounter(testStatelessLocal.getCounter());
	}
}

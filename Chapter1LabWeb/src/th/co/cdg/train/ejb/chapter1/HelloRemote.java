package th.co.cdg.train.ejb.chapter1;

import javax.ejb.Remote;

@Remote
public interface HelloRemote {
	public String hello(String name);
}

package th.co.cdg.train.exam.business;

import javax.ejb.Remote;

import th.co.cdg.train.exam.bean.CustomerBean;
import th.co.cdg.train.exam.bean.OrderBean;

@Remote
public interface OnlineShopBusinessRemote {
	public OrderBean makeOrder(OrderBean orderBean);
	public CustomerBean insertCustomer(CustomerBean customerBean);
}

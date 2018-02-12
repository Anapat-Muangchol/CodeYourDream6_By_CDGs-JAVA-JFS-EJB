package th.co.cdg.train.exam.persistence;

import javax.ejb.Local;

import th.co.cdg.train.exam.entity.Customer;
import th.co.cdg.train.exam.entity.OrderDetail;
import th.co.cdg.train.exam.entity.OrderMaster;

@Local
public interface OnlineShopPersistenceLocal {
	public Customer insertCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public OrderMaster insertOrderMaster(OrderMaster orderMaster);
	public OrderDetail insertOrderDetail(OrderDetail orderDetail);
}

package th.co.cdg.train.exam.persistence;

import java.util.List;

import javax.ejb.Local;

import th.co.cdg.train.exam.entity.Category;
import th.co.cdg.train.exam.entity.Customer;
import th.co.cdg.train.exam.entity.OrderDetail;
import th.co.cdg.train.exam.entity.OrderMaster;
import th.co.cdg.train.exam.entity.Product;

@Local
public interface OnlineShopQueryPersistenceLocal {
	public List<Category> queryCategory();
	public List<Product> queryProductByCategoryCode(String categoryCode);
	public Customer queryCustomerById(String customerId);
	public OrderMaster queryOrderMasterById(String orderId);
	public List<OrderDetail> queryOrderDetailByOrderId(String orderId);
	public String queryNextCustomerId();
	public String queryNextOrderId();
	public String queryNextOrderDetailId();
	
}

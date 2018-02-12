package th.co.cdg.train.exam.business;

import java.util.List;

import javax.ejb.Remote;

import th.co.cdg.train.exam.bean.CategoryBean;
import th.co.cdg.train.exam.bean.CustomerBean;
import th.co.cdg.train.exam.bean.OrderBean;
import th.co.cdg.train.exam.bean.ProductBean;

@Remote
public interface OnlineShopQueryRemote {
	public List<CategoryBean> queryCategory();
	public List<ProductBean> queryProductByCategoryCode(String categoryCode);
	public CustomerBean queryCustomerById(String customerId);
	public OrderBean queryPrintOrder(String orderId);
}

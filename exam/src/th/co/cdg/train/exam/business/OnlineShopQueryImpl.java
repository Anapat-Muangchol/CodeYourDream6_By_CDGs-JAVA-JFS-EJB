package th.co.cdg.train.exam.business;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdg.train.exam.bean.CategoryBean;
import th.co.cdg.train.exam.bean.CustomerBean;
import th.co.cdg.train.exam.bean.OrderBean;
import th.co.cdg.train.exam.bean.OrderDetailBean;
import th.co.cdg.train.exam.bean.ProductBean;
import th.co.cdg.train.exam.convert.ConvertObjects;
import th.co.cdg.train.exam.entity.Category;
import th.co.cdg.train.exam.entity.Customer;
import th.co.cdg.train.exam.entity.OrderDetail;
import th.co.cdg.train.exam.entity.OrderMaster;
import th.co.cdg.train.exam.entity.Product;
import th.co.cdg.train.exam.persistence.OnlineShopQueryPersistenceImpl;

/**
 * Session Bean implementation class OnlineShopQueryImpl
 */
@Stateless
@LocalBean
public class OnlineShopQueryImpl implements OnlineShopQueryRemote, OnlineShopQueryLocal {

	@PersistenceContext(unitName = "trainexam")
	private EntityManager em;
	
	@EJB
	private OnlineShopQueryPersistenceImpl onlineShopQueryPersistenceImpl;
	
	private ConvertObjects convert = new ConvertObjects();
	
	@Override
	public List<CategoryBean> queryCategory() {
		List<Category> cList = onlineShopQueryPersistenceImpl.queryCategory();
		
		List<CategoryBean> cBList = new ArrayList<CategoryBean>();
		for (Category category : cList) {
			cBList.add(convert.toCategoryBean(category));
		}
		return cBList;
	}

	@Override
	public List<ProductBean> queryProductByCategoryCode(String categoryCode) {
		List<Product> pList = onlineShopQueryPersistenceImpl.queryProductByCategoryCode(categoryCode);
		
		List<ProductBean> pBList = new ArrayList<ProductBean>();
		for (Product product : pList) {
			pBList.add(convert.toProductBean(product));
		}
		return pBList;
	}

	@Override
	public CustomerBean queryCustomerById(String customerId) {
		Customer cb = onlineShopQueryPersistenceImpl.queryCustomerById(customerId);
		return convert.toCustomerBean(cb);
	}

	@Override
	public OrderBean queryPrintOrder(String orderId) {
		OrderMaster om = onlineShopQueryPersistenceImpl.queryOrderMasterById(orderId);
		
		OrderBean ob = new OrderBean();
		ob.setOrderId(om.getOrderId());
		ob.setAmount(om.getAmount());
		ob.setOrderDate(om.getOrderDate());
		ob.setTotal(om.getTotal());
		
		Customer customer = onlineShopQueryPersistenceImpl.queryCustomerById(om.getCustomer().getCustomerId());
		ob.setCustomerBean(convert.toCustomerBean(customer));
		
		List<OrderDetail> odList = onlineShopQueryPersistenceImpl.queryOrderDetailByOrderId(om.getOrderId());
		List<OrderDetailBean> odBeanList = new ArrayList<OrderDetailBean>();
		for (OrderDetail od : odList) {
			odBeanList.add(convert.toOrderDetailBean(od));
		}
		ob.setOrderDetails(odBeanList);
		
		return ob;
	}
	
	
}

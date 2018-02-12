package th.co.cdg.train.exam.business;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdg.train.exam.bean.CustomerBean;
import th.co.cdg.train.exam.bean.OrderBean;
import th.co.cdg.train.exam.bean.OrderDetailBean;
import th.co.cdg.train.exam.convert.ConvertObjects;
import th.co.cdg.train.exam.entity.Customer;
import th.co.cdg.train.exam.entity.OrderMaster;
import th.co.cdg.train.exam.persistence.OnlineShopPersistenceLocal;
import th.co.cdg.train.exam.persistence.OnlineShopQueryPersistenceImpl;
import th.co.cdg.train.exam.persistence.OnlineShopQueryPersistenceLocal;

/**
 * Session Bean implementation class OnlineShopBusinessImpl
 */
@Stateless
@LocalBean
public class OnlineShopBusinessImpl implements OnlineShopBusinessRemote, OnlineShopBusinessLocal {

	@PersistenceContext(unitName = "trainexam")
	private EntityManager em;
	
	@EJB
	private OnlineShopPersistenceLocal onlineShopPersistenceLocal;
	
	@EJB
	private OnlineShopQueryPersistenceLocal onlineShopQueryPersistenceLocal;
	
	private ConvertObjects convert = new ConvertObjects();
	
	@Override
	public OrderBean makeOrder(OrderBean orderBean) {
		if(orderBean != null && orderBean.getOrderDetails() != null){
			if(orderBean.getCustomerBean() != null && orderBean.getCustomerBean().getCustomerId() != null && !"".equals(orderBean.getCustomerBean())){
				Customer c = onlineShopQueryPersistenceLocal.queryCustomerById(orderBean.getCustomerBean().getCustomerId());
				if(c != null){
					onlineShopPersistenceLocal.updateCustomer(convert.toCustomer(orderBean.getCustomerBean()));
				}else{
					onlineShopPersistenceLocal.insertCustomer(convert.toCustomer(orderBean.getCustomerBean()));
				}
			}else{
				onlineShopPersistenceLocal.insertCustomer(convert.toCustomer(orderBean.getCustomerBean()));
			}
			
			OrderMaster om = new OrderMaster();
			om.setAmount(orderBean.getAmount());
			om.setOrderDate(orderBean.getOrderDate());
			om.setOrderId(orderBean.getOrderId());
			om.setTotal(orderBean.getTotal());
			
			OrderMaster omFromDb = onlineShopPersistenceLocal.insertOrderMaster(om);
			
			
			if(orderBean.getOrderDetails() != null){
				for (OrderDetailBean od : orderBean.getOrderDetails()) {
					onlineShopPersistenceLocal.insertOrderDetail(convert.toOrderDetail(od));
				}
			}
			
		}
		return null;
	}

	@Override
	public CustomerBean insertCustomer(CustomerBean customerBean) {
		if(customerBean != null){
			Customer c = convert.toCustomer(customerBean);
			Customer returnCus = onlineShopPersistenceLocal.insertCustomer(c);
			return convert.toCustomerBean(returnCus);
		}
		
		return null;
	}
	
	
}

package th.co.cdg.train.exam.persistence;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import th.co.cdg.train.exam.entity.Customer;
import th.co.cdg.train.exam.entity.OrderDetail;
import th.co.cdg.train.exam.entity.OrderMaster;

/**
 * Session Bean implementation class OnlineShopPersistenceImpl
 */
@Stateless
@LocalBean
public class OnlineShopPersistenceImpl implements OnlineShopPersistenceLocal {

	@PersistenceContext(unitName = "trainexam")
	private EntityManager em;
	
	@EJB
	private OnlineShopQueryPersistenceImpl onlineShopQueryPersistenceImpl;

	@Override
	public Customer insertCustomer(Customer customer) {
		if(customer != null){
			String newCusId = onlineShopQueryPersistenceImpl.queryNextCustomerId();
			customer.setCustomerId(newCusId);
			em.persist(customer);
			return customer;
		}
		return null;
	}

	@Override
	public Customer updateCustomer(Customer customer) {
		if(customer != null){
			em.merge(customer);
			return customer;
		}
		return null;
	}

	@Override
	public OrderMaster insertOrderMaster(OrderMaster orderMaster) {
		if(orderMaster != null){
			String newOrderId = onlineShopQueryPersistenceImpl.queryNextOrderId();
			orderMaster.setOrderId(newOrderId);
			em.persist(orderMaster);
			return orderMaster;
		}
		return null;
	}

	@Override
	public OrderDetail insertOrderDetail(OrderDetail orderDetail) {
		if(orderDetail != null){
			String newOrderDetailId = onlineShopQueryPersistenceImpl.queryNextOrderDetailId();
			orderDetail.setOrderDetailId(newOrderDetailId);;
			em.persist(orderDetail);
			return orderDetail;
		}
		return null;
	}
	
	

}

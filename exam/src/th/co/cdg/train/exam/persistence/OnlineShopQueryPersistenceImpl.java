package th.co.cdg.train.exam.persistence;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import th.co.cdg.train.exam.entity.Category;
import th.co.cdg.train.exam.entity.Customer;
import th.co.cdg.train.exam.entity.OrderDetail;
import th.co.cdg.train.exam.entity.OrderMaster;
import th.co.cdg.train.exam.entity.Product;

/**
 * Session Bean implementation class OnlineShopQueryPersistenceImpl
 */
@Stateless
@LocalBean
public class OnlineShopQueryPersistenceImpl implements OnlineShopQueryPersistenceLocal {
	
	@PersistenceContext(unitName = "trainexam")
	private EntityManager em;

	@Override
	public List<Category> queryCategory() {
		final String jpql = "select c from Category c order by c.categoryCode";
		Query query = em.createQuery(jpql);
		return (List<Category>) query.getResultList();
	}

	@Override
	public List<Product> queryProductByCategoryCode(String categoryCode) {
		if(categoryCode == null || "".equals(categoryCode)){
			throw new IllegalArgumentException("The \'categoryCode\' parameter is blank or null");
		}
		
		final String jpql = "select p from Product p where p.category.categoryCode = :categoryCode order by p.productCode";
		Query query = em.createQuery(jpql);
		query.setParameter("categoryCode", categoryCode);
		return (List<Product>) query.getResultList();
	}

	@Override
	public Customer queryCustomerById(String customerId) {
		if(customerId == null || "".equals(customerId)){
			throw new IllegalArgumentException("The \'customerId\' parameter is blank or null");
		}
		
		final String jpql = "select c from Customer c where c.customerId = :customerId";
		Query query = em.createQuery(jpql);
		query.setParameter("customerId", customerId);
		return (Customer) query.getSingleResult();
	}

	@Override
	public OrderMaster queryOrderMasterById(String orderId) {
		if(orderId == null || "".equals(orderId)){
			throw new IllegalArgumentException("The \'orderId\' parameter is blank or null");
		}

		final String jpql = "select om from OrderMaster om where om.orderId = :orderId";
		Query query = em.createQuery(jpql);
		query.setParameter("orderId", orderId);
		return (OrderMaster) query.getSingleResult();
	}

	@Override
	public List<OrderDetail> queryOrderDetailByOrderId(String orderId) {
		if(orderId == null || "".equals(orderId)){
			throw new IllegalArgumentException("The \'orderId\' parameter is blank or null");
		}

		final String sql = "select od.order_detail_id, od.order_id, od.product_code, od.product_amount, od.product_total, d.product_name, d.pricefrom order_detail od"
							+ " join product d on (od.product_code = d.product_code)"
							+ " where order_id = :orderId"
							+ " order by od.order_detail_id";
		
		Query query = em.createNativeQuery(sql);

		query.setParameter("orderId", orderId);

		Object o = query.getResultList();

		if (o != null) {

			List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
			if (o instanceof Object[]) {
				Object[] resultObject = (Object[]) o;

				OrderDetail od = new OrderDetail();
				od.setOrderDetailId((String) resultObject[0]);
				
				OrderMaster om = new OrderMaster();
				om.setOrderId((String) resultObject[1]);
				od.setOrderMaster(om);
				
				Product p = new Product();
				p.setProductCode((String) resultObject[2]);
				
				p.setProductCode((String) resultObject[2]);
				
				od.setProductAmount((Integer) resultObject[3]);
				od.setProductTotal((Integer) resultObject[4]);
				
				p.setProductName((String) resultObject[5]);
				p.setPrice((Integer) resultObject[6]);
				
				od.setProduct(p);
				
				orderDetails.add(od);
			} else {
				List<Object[]> resultObjects = (List<Object[]>) o;
				for (Object[] resultObject : resultObjects) {

					OrderDetail od = new OrderDetail();
					od.setOrderDetailId((String) resultObject[0]);
					
					OrderMaster om = new OrderMaster();
					om.setOrderId((String) resultObject[1]);
					od.setOrderMaster(om);
					
					Product p = new Product();
					p.setProductCode((String) resultObject[2]);
					
					p.setProductCode((String) resultObject[2]);
					
					od.setProductAmount((Integer) resultObject[3]);
					od.setProductTotal((Integer) resultObject[4]);
					
					p.setProductName((String) resultObject[5]);
					p.setPrice((Integer) resultObject[6]);
					
					od.setProduct(p);
					
					orderDetails.add(od);
				}
			}

			return orderDetails;
		}
		
		return null;
	}

	@Override
	public String queryNextCustomerId() {
		String sql = "select coalesce(max(customer_id) + 1 , 1) from customer";
		
		Query query = em.createNativeQuery(sql);
		
		Object o = query.getSingleResult();
		
		String unpadded = String.valueOf(((Double)o).intValue()); 
		String padded = "00000".substring(unpadded.length()) + unpadded;
		
		return padded;
	}

	@Override
	public String queryNextOrderId() {
		String sql = "select coalesce(max(order_id) + 1 , 1 ) from order_master";
		
		Query query = em.createNativeQuery(sql);
		
		Object o = query.getSingleResult();
		
		String unpadded = String.valueOf(((Double)o).intValue()); 
		String padded = "00000".substring(unpadded.length()) + unpadded;
		
		return padded;
	}

	@Override
	public String queryNextOrderDetailId() {
		String sql = "select coalesce(max(order_detail_id) + 1 , 1 ) from order_detail";
		
		Query query = em.createNativeQuery(sql);
		
		Object o = query.getSingleResult();
		
		String unpadded = String.valueOf(((Double)o).intValue()); 
		String padded = "00000".substring(unpadded.length()) + unpadded;
		
		return padded;
	}

    
}

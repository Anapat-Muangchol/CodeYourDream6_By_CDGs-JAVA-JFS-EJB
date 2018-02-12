package th.co.cdg.train.exam.convert;

import th.co.cdg.train.exam.bean.CategoryBean;
import th.co.cdg.train.exam.bean.CustomerBean;
import th.co.cdg.train.exam.bean.OrderDetailBean;
import th.co.cdg.train.exam.bean.ProductBean;
import th.co.cdg.train.exam.entity.Category;
import th.co.cdg.train.exam.entity.Customer;
import th.co.cdg.train.exam.entity.OrderDetail;
import th.co.cdg.train.exam.entity.OrderMaster;
import th.co.cdg.train.exam.entity.Product;

public class ConvertObjects {
	
	// ==================== Entity to Bean ====================
	
	public CategoryBean toCategoryBean(Category c){
		CategoryBean cb = new CategoryBean();
		cb.setCategoryCode(c.getCategoryCode());
		cb.setCategoryName(c.getCategoryName());
		return cb;
	}
	
	public ProductBean toProductBean(Product p){
		ProductBean pb = new ProductBean();
		pb.setProductCode(p.getProductCode());
		pb.setProductName(p.getProductName());
		pb.setCategoryCode(p.getCategory().getCategoryCode());
		pb.setDetail(p.getDetail());
		pb.setPrice(p.getPrice());
		return pb;
	}
	
	public CustomerBean toCustomerBean(Customer customer){
		CustomerBean cb = new CustomerBean();
		cb.setCustomerId(customer.getCustomerId());
		cb.setFirstName(customer.getFirstName());
		cb.setLastName(customer.getLastName());
		cb.setAddress(customer.getAddress());
		return cb;
	}
	
	public OrderDetailBean toOrderDetailBean(OrderDetail orderDetail){
		OrderDetailBean odb = new OrderDetailBean();
		odb.setOrderDetailId(orderDetail.getOrderDetailId());
		odb.setOrderId(orderDetail.getOrderMaster().getOrderId());
		odb.setProductAmount(orderDetail.getProductAmount());
		odb.setProductTotal(orderDetail.getProductTotal());
		odb.setProductBean(toProductBean(orderDetail.getProduct()));
		
		return odb;
	}
	// ==================== Bean to Entity ====================
	
	public Customer toCustomer(CustomerBean customerBean){
		Customer c = new Customer();
		c.setCustomerId(customerBean.getCustomerId());
		c.setFirstName(customerBean.getFirstName());
		c.setLastName(customerBean.getLastName());
		c.setAddress(customerBean.getAddress());
		return c;
	}
	
	public OrderDetail toOrderDetail(OrderDetailBean orderDetailBean){
		OrderDetail d = new OrderDetail();
		d.setOrderDetailId(orderDetailBean.getOrderDetailId());
		d.setProductAmount(orderDetailBean.getProductAmount());
		d.setProductTotal(orderDetailBean.getProductTotal());
		
		return d;
	}
}

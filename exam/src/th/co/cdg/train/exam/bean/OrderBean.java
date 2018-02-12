package th.co.cdg.train.exam.bean;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class OrderBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2922919153352266031L;
	private String orderId;
	private CustomerBean customerBean;
	private Date orderDate;
	private Integer total;
	private Integer amount;
	private List<OrderDetailBean> orderDetails;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public CustomerBean getCustomerBean() {
		return customerBean;
	}
	public void setCustomerBean(CustomerBean customerBean) {
		this.customerBean = customerBean;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public List<OrderDetailBean> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetailBean> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}

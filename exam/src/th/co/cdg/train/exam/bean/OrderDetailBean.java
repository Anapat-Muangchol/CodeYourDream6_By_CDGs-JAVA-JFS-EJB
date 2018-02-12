package th.co.cdg.train.exam.bean;

import java.io.Serializable;

public class OrderDetailBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8134877633533776836L;
	private String orderDetailId;
	private String orderId;
	private ProductBean productBean;
	private Integer productAmount;
	private Integer productTotal;
	
	public String getOrderDetailId() {
		return orderDetailId;
	}
	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public ProductBean getProductBean() {
		return productBean;
	}
	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
	}
	public Integer getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(Integer productAmount) {
		this.productAmount = productAmount;
	}
	public Integer getProductTotal() {
		return productTotal;
	}
	public void setProductTotal(Integer productTotal) {
		this.productTotal = productTotal;
	}
	
	
}

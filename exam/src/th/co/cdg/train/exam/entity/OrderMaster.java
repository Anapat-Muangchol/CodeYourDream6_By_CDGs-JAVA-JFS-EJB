package th.co.cdg.train.exam.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the order_master database table.
 * 
 */
@Entity
@Table(name="order_master")
@NamedQuery(name="OrderMaster.findAll", query="SELECT o FROM OrderMaster o")
public class OrderMaster implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderId;
	private int amount;
	private Date orderDate;
	private int total;
	private List<OrderDetail> orderDetails;
	private Customer customer;

	public OrderMaster() {
	}


	@Id
	@Column(name="order_id", unique=true, nullable=false, length=5)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	@Column(nullable=false)
	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_date", nullable=false)
	public Date getOrderDate() {
		return this.orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}


	@Column(nullable=false)
	public int getTotal() {
		return this.total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	//bi-directional many-to-one association to OrderDetail
	@OneToMany(mappedBy="orderMaster", fetch=FetchType.EAGER)
	public List<OrderDetail> getOrderDetails() {
		return this.orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public OrderDetail addOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().add(orderDetail);
		orderDetail.setOrderMaster(this);

		return orderDetail;
	}

	public OrderDetail removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		orderDetail.setOrderMaster(null);

		return orderDetail;
	}


	//bi-directional many-to-one association to Customer
	@ManyToOne
	@JoinColumn(name="customer_id", nullable=false)
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
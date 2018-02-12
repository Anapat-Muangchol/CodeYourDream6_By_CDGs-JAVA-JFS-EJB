package th.co.cdg.train.exam.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the order_detail database table.
 * 
 */
@Entity
@Table(name="order_detail")
@NamedQuery(name="OrderDetail.findAll", query="SELECT o FROM OrderDetail o")
public class OrderDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderDetailId;
	private int productAmount;
	private int productTotal;
	private OrderMaster orderMaster;
	private Product product;

	public OrderDetail() {
	}


	@Id
	@Column(name="order_detail_id", unique=true, nullable=false, length=10)
	public String getOrderDetailId() {
		return this.orderDetailId;
	}

	public void setOrderDetailId(String orderDetailId) {
		this.orderDetailId = orderDetailId;
	}


	@Column(name="product_amount", nullable=false)
	public int getProductAmount() {
		return this.productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}


	@Column(name="product_total", nullable=false)
	public int getProductTotal() {
		return this.productTotal;
	}

	public void setProductTotal(int productTotal) {
		this.productTotal = productTotal;
	}


	//bi-directional many-to-one association to OrderMaster
	@ManyToOne
	@JoinColumn(name="order_id", nullable=false)
	public OrderMaster getOrderMaster() {
		return this.orderMaster;
	}

	public void setOrderMaster(OrderMaster orderMaster) {
		this.orderMaster = orderMaster;
	}


	//bi-directional many-to-one association to Product
	@ManyToOne
	@JoinColumn(name="product_code", nullable=false)
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
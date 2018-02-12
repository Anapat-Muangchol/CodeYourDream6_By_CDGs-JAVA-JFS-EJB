package th.co.cdg.train.exam.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@Table(name="customer")
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String customerId;
	private String address;
	private String firstName;
	private String lastName;
	private List<OrderMaster> orderMasters;

	public Customer() {
	}


	@Id
	@Column(name="customer_id", unique=true, nullable=false, length=5)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	@Column(nullable=false, length=500)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	@Column(name="first_name", nullable=false, length=50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	@Column(name="last_name", nullable=false, length=50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	//bi-directional many-to-one association to OrderMaster
	@OneToMany(mappedBy="customer", fetch=FetchType.EAGER)
	public List<OrderMaster> getOrderMasters() {
		return this.orderMasters;
	}

	public void setOrderMasters(List<OrderMaster> orderMasters) {
		this.orderMasters = orderMasters;
	}

	public OrderMaster addOrderMaster(OrderMaster orderMaster) {
		getOrderMasters().add(orderMaster);
		orderMaster.setCustomer(this);

		return orderMaster;
	}

	public OrderMaster removeOrderMaster(OrderMaster orderMaster) {
		getOrderMasters().remove(orderMaster);
		orderMaster.setCustomer(null);

		return orderMaster;
	}

}
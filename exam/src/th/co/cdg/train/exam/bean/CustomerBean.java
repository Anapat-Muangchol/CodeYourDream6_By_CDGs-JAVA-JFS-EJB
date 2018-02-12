package th.co.cdg.train.exam.bean;

import java.io.Serializable;

public class CustomerBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3025238781714857690L;
	private String customerId;
	private String firstName;
	private String lastName;
	private String address;
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}

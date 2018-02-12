package th.co.cdg.train.exam.bean;

import java.io.Serializable;
import java.util.List;

import th.co.cdg.train.exam.entity.Category;
import th.co.cdg.train.exam.entity.OrderDetail;

public class ProductBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8487295455065562948L;
	private String productCode;
	private String productName;
	private Integer price;
	private String categoryCode;
	private String detail;
	private Integer amount;
	private Integer totle;
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getTotle() {
		return totle;
	}
	public void setTotle(Integer totle) {
		this.totle = totle;
	}
	
	
}

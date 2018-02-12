package th.co.cdg.train.exam.bean;

import java.io.Serializable;

public class CategoryBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1320545088130491683L;
	private String categoryCode;
	private String categoryName;
	
	
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}

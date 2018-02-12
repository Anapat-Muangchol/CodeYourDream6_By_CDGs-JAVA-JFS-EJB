package th.co.cdg.train.exam.manageBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

import th.co.cdg.train.exam.bean.CategoryBean;
import th.co.cdg.train.exam.bean.ProductBean;
import th.co.cdg.train.exam.business.OnlineShopQueryLocal;

@ManagedBean
@SessionScoped
public class indexCtrl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7130633077363348115L;

	@EJB
	private OnlineShopQueryLocal OnlineShopQuery;

	private List<CategoryBean> categorys;
	private String categoryCode;
	private int numDisplay = 5;
	private int cartNum = 0;

	private List<ProductBean> products;
	private Map<String, ProductBean> productsReal = new TreeMap<String, ProductBean>();
	private List<ProductBean> chooseProducts = new ArrayList<ProductBean>();
	
	private ProductBean productDetail;

	// ================ Getter & Setter ================

	public OnlineShopQueryLocal getOnlineShopQuery() {
		return OnlineShopQuery;
	}

	public void setOnlineShopQuery(OnlineShopQueryLocal onlineShopQuery) {
		OnlineShopQuery = onlineShopQuery;
	}

	public List<CategoryBean> getCategorys() {
		if (categorys == null) {
			categorys = OnlineShopQuery.queryCategory();
			categoryCode = categorys.get(0).getCategoryCode();
		}
		return categorys;
	}

	public void setCategorys(List<CategoryBean> categorys) {
		this.categorys = categorys;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public int getNumDisplay() {
		return numDisplay;
	}

	public void setNumDisplay(int numDisplay) {
		this.numDisplay = numDisplay;
	}

	public int getCartNum() {
		return cartNum;
	}

	public void setCartNum(int cartNum) {
		this.cartNum = cartNum;
	}

	public List<ProductBean> getProducts() {
		if (products == null) {
			if (categoryCode == null || "".equals(categoryCode)) {
				getCategorys();
			}
			
			products = OnlineShopQuery.queryProductByCategoryCode(categoryCode);
			
			productsReal = new TreeMap<String, ProductBean>();
			for (ProductBean p : products) {
				productsReal.put(p.getProductCode(), copyProduct(p));
			}

			if (products.size() > numDisplay) {
				for (int i = numDisplay; i < products.size();) {
					products.remove(i);
				}
			}

			for (int i = 0; i < products.size(); i++) {
				String detail = products.get(i).getDetail();
				products.get(i).setDetail(detail.substring(0, 50));
			}

		}
		return products;
	}

	public void setProducts(List<ProductBean> products) {
		this.products = products;
	}

	public List<ProductBean> getChooseProducts() {
		return chooseProducts;
	}

	public void setChooseProducts(List<ProductBean> chooseProducts) {
		this.chooseProducts = chooseProducts;
	}
	
	public ProductBean getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(ProductBean productDetail) {
		this.productDetail = productDetail;
	}
	
	// ================ Methods ================
	
	private ProductBean copyProduct(ProductBean p){
		ProductBean pNew = new ProductBean();
		pNew.setAmount(p.getAmount());
		pNew.setCategoryCode(p.getCategoryCode());
		pNew.setDetail(p.getDetail());
		pNew.setPrice(p.getPrice());
		pNew.setProductCode(p.getProductCode());
		pNew.setProductName(p.getProductName());
		pNew.setTotle(p.getTotle());
		return pNew;
	}
	
	public void changeCategory(ValueChangeEvent event) {
		categoryCode = (String) event.getNewValue();
		
		products = OnlineShopQuery.queryProductByCategoryCode(categoryCode);
		
		productsReal = new TreeMap<String, ProductBean>();
		for (ProductBean p : products) {
			productsReal.put(p.getProductCode(), copyProduct(p));
		}
		
		if (products.size() > numDisplay) {
			for (int i = numDisplay; i < products.size();) {
				products.remove(i);
			}
		}

		for (int i = 0; i < products.size(); i++) {
			String detail = products.get(i).getDetail();
			products.get(i).setDetail(detail.substring(0, 50));
		}
	}

	public void changeNumDisplay(ValueChangeEvent event) {
		numDisplay = (Integer) event.getNewValue();

		products = OnlineShopQuery.queryProductByCategoryCode(categoryCode);
		
		productsReal = new TreeMap<String, ProductBean>();
		for (ProductBean p : products) {
			productsReal.put(p.getProductCode(), copyProduct(p));
		}
		
		if (products.size() > numDisplay) {
			for (int i = numDisplay; i < products.size();) {
				products.remove(i);
			}
		}

		for (int i = 0; i < products.size(); i++) {
			String detail = products.get(i).getDetail();
			products.get(i).setDetail(detail.substring(0, 50));
		}
	}
	
	public String viewDetailProduct(String productCode){
		productDetail = productsReal.get(productCode);
		return "view";
	}
	
	public void choose(String productCode){
		System.out.println("productCode:"+productCode);
		chooseProducts.add(productsReal.get(productCode));
		cartNum = chooseProducts.size();
	}
}

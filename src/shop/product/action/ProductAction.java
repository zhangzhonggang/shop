package shop.product.action;

import shop.category.service.CategoryService;
import shop.product.service.ProductService;
import shop.product.vo.Product;
import shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 商品的Action对象
 * 
 * @author Programmer
 *
 */
public class ProductAction extends ActionSupport implements
		ModelDriven<Product> {

	// 用于接收数据的模型驱动
	private Product product = new Product();

	public Product getModel() {
		return product;
	}

	// 注入商品的Service
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	// 接收分类cid
	private Integer cid;

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	// 接收二级分类csid
	private Integer csid;

	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	// 接收当前页数
	private int page;

	public void setPage(int page) {
		this.page = page;
	}

	// 注入一级分类的Service
	private CategoryService categoryService;

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	// 根据商品id进行商品查询
	public String findByPid() {
		// 调用Service的方法完成调用
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}

	// 根据分类的ID查询商品
	public String findByCid() {
		PageBean<Product> pageBean = productService.findByPageCid(cid, page); // 根据一级分类查询商品，带分页的查询
		// 将pageBean存入到值栈中：
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}

	// 根据二级分类
	public String findByCsid() {
		// 根据二级分类查询商品
		PageBean<Product> pageBean = productService.findByCsid(csid, page);
		// 将pageBean存入到值栈中：
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}

}

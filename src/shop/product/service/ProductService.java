package shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import shop.product.dao.ProductDao;
import shop.product.vo.Product;
import shop.utils.PageBean;

/**
 * 商品的业务层代码
 * 
 * @author Programmer
 *
 */
@Transactional
public class ProductService {
	// 注入ProductDao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 首页上热门商品的查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	// 首页上最新商品的查询
	public List<Product> findNew() {
		return productDao.findNew();
	}

	// 根据商品ID查询商品
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	// 根据一级分类的cid带分页查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数：
		pageBean.setPage(page);
		// 设置每页显示记录数：
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总记录数：
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数：
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合
		int begin = (page - 1) * limit;

		List<Product> list = productDao.findByPageCid(cid, begin, limit);
		pageBean.setList(list);

		return pageBean;
	}

	public PageBean<Product> findByCsid(Integer csid, int page) {
		// 根据二级分类查询商品
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数：
		pageBean.setPage(page);
		// 设置每页显示记录数：
		int limit = 8;
		pageBean.setLimit(limit);
		// 设置总记录数：
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数：
		int totalPage = 0;
		if (totalCount % limit == 0) {
			totalPage = totalCount / limit;
		} else {
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合
		int begin = (page - 1) * limit;

		List<Product> list = productDao.findByPageCsid(csid, begin, limit);
		pageBean.setList(list);

		return pageBean;
	}

}

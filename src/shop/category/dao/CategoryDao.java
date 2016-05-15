package shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport {

	// DAO层查询所有一级分类的方法
	public List<Category> findAll() {
		String hql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	// DAO层保存一级分类的方法
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	// DAO层根据cid查询一级分类
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	// DAO层删除一级分类的方法
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

}

package shop.order.dao;

import java.util.Date;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import shop.order.vo.Order;
import shop.user.vo.User;
import shop.utils.PageHibernateCallback;

/**
 * 订单模块持久层代码
 * 
 * @author Programmer
 *
 */
public class OrderDao extends HibernateDaoSupport {

	// 保存订单的DAO层方法
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	// DAO层我的订单个数统计
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return null;
	}
	

	// DAO层我的订单查询
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		return list;
	}

	// 根据订单ID查询订单DAO层代码
	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	// Dao层的修改订单的操作
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

	// DAO层统计订单总数的方法
	public int findByCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// DAO层的带分页查询的方法
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, null, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

}

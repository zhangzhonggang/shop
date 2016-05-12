package shop.order.dao;

import java.util.Date;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import shop.order.vo.Order;
import shop.user.vo.User;

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

}

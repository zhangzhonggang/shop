package shop.order.service;

import org.springframework.transaction.annotation.Transactional;

import shop.order.dao.OrderDao;
import shop.order.vo.Order;
import shop.utils.PageBean;

/**
 * 订单模块业务层代码
 * 
 * @author Programmer
 *
 */
@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	// 保存订单的业务层代码
	public void save(Order order) {
		orderDao.save(order);
	}

	// 查询我的订单的业务层代码
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数：
		pageBean.setPage(page);
		// 设置每页显示的记录数：
		Integer limit = 5;
		pageBean.setLimit(limit);
		
		return pageBean;
	}
}

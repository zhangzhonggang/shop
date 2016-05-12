package shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import shop.cart.vo.Cart;
import shop.cart.vo.CartItem;
import shop.order.service.OrderService;
import shop.order.vo.Order;
import shop.order.vo.OrderItem;
import shop.user.vo.User;
import shop.utils.PageBean;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 订单管理的Action
 * 
 * @author Programmer
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order> {

	// 模型驱动使用的对象
	private Order order = new Order();

	public Order getModel() {
		return order;
	}

	// 注入OrderService
	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	
	// 接收page参数：
	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}

	// 生成订单的方法
	public String save() {
		// 1.保存数据到数据库
		// 订单数据补全
		order.setOrdertime(new Date());
		order.setState(1); // 1：未付款；2：已付款，但没发货；3：已发货，没确认收货；4：完成交易
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if (cart == null) {
			this.addActionError("购物车是空的，先去逛逛吧！");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		// 设置订单中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);

			order.getOrderItems().add(orderItem);
		}
		// 设置订单所属用户
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if (existUser == null) {
			this.addActionError("您还没有登录，请先去登录！");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		// 2.将订单对象显示到页面上
		// 通过值栈的方式显示：
		return "saveSuccess";
	}
	
	// 我的订单的查询
	public String findByUid() {
		// 根据用户的ID查询
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(), page);
	}
	
	
	
	
	
	
	
	
}

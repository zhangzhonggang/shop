package shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 * 
 * @author Programmer
 *
 */
public class Cart {
	// 购物项集合：Map的key是商品ID，value是购物项
	Map<Integer, CartItem> map = new LinkedHashMap<Integer, CartItem>();
	
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	// 购物总计：
	private double total;

	public void setTotal(double total) {
		this.total = total;
	}

	public double getTotal() {
		return total;
	}

	// 购物车的功能：
	// 1.将购物项添加到购物车
	public void addCart(CartItem cartItem) {
		Integer pid = cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		} else {
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}

	// 2.从购物车移除购物项
	public void removeCart(Integer pid) {
		// 将购物项移除购物车
		CartItem cartItem = map.remove(pid);
		// 总计 = 总计-移除的购物项小计
		total -= cartItem.getSubtotal();
	}

	// 3.清空购物车
	public void clearCart() {
		// 将所有购物项清空
		map.clear();
		// 将合计设置为0
		total = 0;
	}
}

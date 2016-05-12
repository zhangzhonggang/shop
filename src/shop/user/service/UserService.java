package shop.user.service;

import org.springframework.transaction.annotation.Transactional;

import shop.user.dao.UserDao;
import shop.user.vo.User;
import shop.utils.MailUtils;
import shop.utils.UUIDUtils;

/**
 * 用户模块业务层代码：
 * 
 * @author Programmer
 *
 */
@Transactional
public class UserService {

	// 注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	// 按用户名查询用户的方法
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	// 业务层完成用户注册的代码
	public void save(User user) {
		// 将数据存入到数据库
		user.setState(0); // 0代表用户未激活，1代表用户已激活
		String code = UUIDUtils.getUUID() + UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);

		// 发送激活邮件
		MailUtils.sendMail(user.getEmail(), code);
	}

	// 业务层 根据激活码查询用户
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	// 修改用户状态的方法
	public void update(User existUser) {
		userDao.update(existUser);
	}

	// 用户登录的方法
	public User login(User user) {
		return userDao.login(user);
	}
}

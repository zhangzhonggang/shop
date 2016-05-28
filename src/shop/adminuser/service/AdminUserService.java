package shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import shop.adminuser.dao.AdminUserDao;
import shop.adminuser.vo.AdminUser;

/**
 * 后台登录的业务层类
 * 
 * @author Programmer
 *
 */
@Transactional
public class AdminUserService {
	// 注入Dao
	private AdminUserDao adminUserDao;

	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}

	// 后台登录的业务层方法
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}

}

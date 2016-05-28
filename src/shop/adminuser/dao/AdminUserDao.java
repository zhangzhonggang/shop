package shop.adminuser.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import shop.adminuser.vo.AdminUser;

/**
 * 后台登录的Dao类
 * 
 * @author Programmer
 *
 */
public class AdminUserDao extends HibernateDaoSupport {

	// 后台登录的DAO层方法
	public AdminUser login(AdminUser adminUser) {
		String hql = "from AdminUser where username = ? and password = ?";
		List<AdminUser> list = this.getHibernateTemplate().find(hql, adminUser.getUsername(), adminUser.getPassword());
		if (list != null && list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}

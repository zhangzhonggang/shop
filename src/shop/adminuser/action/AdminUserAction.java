package shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import shop.adminuser.service.AdminUserService;
import shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 后台登录的action
 * 
 * @author Programmer
 *
 */
public class AdminUserAction extends ActionSupport implements
		ModelDriven<AdminUser> {

	// 模型驱动使用的对象
	private AdminUser adminUser = new AdminUser();

	// 注入Service
	private AdminUserService adminUserService;
	
	public AdminUser getModel() {
		return adminUser;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	
	// 后台登录的方法：
	public String login() {
		// 调用Service完成登录
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if (existAdminUser == null) {
			this.addActionError("用户名或密码错误！");
			return "loginFail";
		} else {
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}

}

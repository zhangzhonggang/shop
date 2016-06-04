package shop.interceptor;

import org.apache.struts2.ServletActionContext;

import shop.adminuser.vo.AdminUser;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
 * 后台权限校验的拦截器：没有登录的用户不可以访问
 * 
 * @author Programmer
 *
 */
public class PrivilegeInterceptor extends MethodFilterInterceptor {

	// 执行拦截的方法
	protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
		// 判断Session中是否保存了后台用户的信息
		AdminUser existAdminUser = (AdminUser) ServletActionContext.getRequest().getSession().getAttribute("existAdminUser");
		if (existAdminUser == null) {
			// 没有登录
			ActionSupport actionSupport = (ActionSupport) actionInvocation.getAction();
			actionSupport.addActionError("请先登录！");
			return "loginFail";
		} else {
			return actionInvocation.invoke();
		}
	}
}

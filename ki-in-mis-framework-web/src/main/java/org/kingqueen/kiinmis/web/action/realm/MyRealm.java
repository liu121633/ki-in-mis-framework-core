package org.kingqueen.kiinmis.web.action.realm;


import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.kingqueen.kiinmis.model.biz.user.UserBiz;
import org.kingqueen.kiinmis.model.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class MyRealm extends  AuthorizingRealm{

	@Autowired
	private UserBiz userBiz;
	
	public MyRealm(){
		ApplicationContext ac=new ClassPathXmlApplicationContext("spring-ctx.xml");
		this.userBiz = (UserBiz) ac.getBean("userBiz");
	}
	

	public void removeUserAuthorizationInfoCache(String username) {
		SimplePrincipalCollection pc = new SimplePrincipalCollection();
		pc.add(username, super.getName());
		super.clearCachedAuthorizationInfo(pc);
	}
	
	/**
	 * @title: doGetAuthorizationInfo
	 * @description: 每次请求都会进入这个方法进行权限分配
	 * @throws 
	 * @return AuthorizationInfo
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		/*String userName=(String)principals.getPrimaryPrincipal();*/
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		//获取用户的编号
		User user = userBiz.findUserByName(userName);
		//获取角色
		Set<String> roles = userBiz.findRolesByUserName(user.getUserLoginName());
		//获取权限
		Set<String> permissions = userBiz.findPermissionsByRoles(roles);
		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(permissions);
		
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession(); 
		session.setAttribute("roles", roles);
		session.setAttribute("permissions", permissions);
		return authorizationInfo;
	}

	private String userName;
	
	/**
	 * @title: doGetAuthenticationInfo
	 * @description: 登录进入的方法
	 * @throws 
	 * @return AuthenticationInfo
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		//获取登录的用户名
		userName = (String)token.getPrincipal();
		/*ApplicationContext ac=new ClasyyyjjsPathXmlApplicationContext("spring-ctx.xml");
		userBiz = (UserBiz) ac.getBean("userBiz");*/
		
		//按照姓名查询
		User  user = userBiz.findUserByName(userName);
		if(user!=null){
			AuthenticationInfo  authcInfo=new SimpleAuthenticationInfo(user.getUserLoginName(), user.getUserPassword(),"xx");
			doGetAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
			return authcInfo;
		}else{
			return null;
		}
	}

}

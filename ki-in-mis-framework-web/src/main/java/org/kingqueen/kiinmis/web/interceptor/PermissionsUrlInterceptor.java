package org.kingqueen.kiinmis.web.interceptor;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class PermissionsUrlInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String basePath = request.getContextPath();

		String pathAll = request.getRequestURI();

		//公告分页显示判断
		if(pathAll.indexOf("message/quertMessagePage")!=-1||pathAll.indexOf("message/toMessageDetail")!=-1){
			return true;
		}

		// 地址权限判断
		// 如果是管理员就直接进入
		// 获取权限
		Set<String> permissions = (Set<String>) request.getSession().getAttribute("permissions");
		if (permissions != null) {
			if (permissions.contains("gongneng:*")) {
				return true;
			}
		}
		boolean urlBool = true;
		boolean flag = false;
		// 获取路径
		String urlPath = request.getServletPath();
		String xmlUrl = request.getSession().getServletContext().getRealPath("") + File.separator + "WEB-INF"
				+ File.separator + "classes" + File.separator + "urlPermissions.xml";
		try {
			File f = new File(xmlUrl);
			SAXReader reader = new SAXReader();
			Document doc = reader.read(f);
			Element root = doc.getRootElement();
			Element foo;
			for (Iterator i = root.elementIterator("value"); i.hasNext();) {
				foo = (Element) i.next();
				// 判断是否有这个路径
				if (urlPath.indexOf(foo.elementText("url")) != -1) {
					urlBool = false;
					// 判断是否有这个权限
					String p = foo.elementText("permission");
					if (permissions.contains(p)) {
						flag = true;
						break;
					}
				}
				/*
				 * System.out.print("地址:" + foo.elementText("url"));
				 * System.out.println("权限:" + foo.elementText("permission"));
				 */

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//如果配置没有这个地址就让它过
		if(urlBool){
			return urlBool;
		}
		if(flag==false){
			response.sendRedirect(basePath+"/unauthor");
		}
		return flag;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}

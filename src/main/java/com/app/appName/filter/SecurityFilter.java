/**
 *
 */
package com.app.appName.filter;

import com.app.appName.entity.UsersEntity;
import com.app.appName.session.SessionManage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class SecurityFilter implements Filter {
	private static final Log LOG = LogFactory.getLog(SecurityFilter.class);

	private List<String> notFilterURIs;
	private List<Pattern> notFilterPatterns = new ArrayList<Pattern>();
	private String LOGIN_URL;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession();
		String path = request.getContextPath();
		String requestURI = request.getRequestURI();
		requestURI = requestURI.substring(requestURI.indexOf(path) + path.length());
		if ((requestURI.startsWith("/manage/") || requestURI.startsWith("/blog/")) && this.isNeedFilter(requestURI)) {
			UsersEntity user = SessionManage.getUserEntity(session);

			if (user == null) {
				if(request.getHeader("x-requested-with")!=null &&
						request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
					PrintWriter out = response.getWriter();
					out.print("sessionTimeOut");
					out.flush();
					out.close();
				}else{
					redirectTo(request, response, LOGIN_URL);
				}

				return;
			}
		}

		chain.doFilter(servletRequest, servletResponse);
	}

	private void redirectTo(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		if (url.startsWith("http")) {
			response.sendRedirect(url);
		} else {
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path;
			response.sendRedirect( basePath + url );
		}
	}

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		this.notFilterURIs = new ArrayList<String>();
		String notFilterURIStr = cfg.getInitParameter("notFilterURIs");
		LOGIN_URL =  cfg.getInitParameter("loginURL");
		if (LOGIN_URL != null) {
			this.notFilterURIs.add(LOGIN_URL);
			this.notFilterPatterns.add(Pattern.compile(LOGIN_URL));
		}else {
			LOG.info("can't config Filter initParameter: loginURL.");
		}
		if (notFilterURIStr != null) {
			String[] strAttr = notFilterURIStr.split(",");
			for (String str : strAttr) {
				this.notFilterURIs.add(str);
				this.notFilterPatterns.add(Pattern.compile(str));
			}
		}
	}

	private boolean isNeedFilter(String requestURI) {
		for (Pattern pattern : notFilterPatterns) {
			if (pattern.matcher(requestURI).matches()) {
				return false;
			}
		}
		return true;
	}

}

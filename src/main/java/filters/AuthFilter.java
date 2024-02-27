package filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.UserBean;

import java.io.IOException;

@WebFilter(urlPatterns = { "/articles", "/articles.jsp", "/users", "/users.jsp" }, filterName = "AuthFilter")
public class AuthFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	public AuthFilter() {
		super();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
		// Check if the user is logged in
		if (session == null || session.getAttribute("user") == null) {
			// Not logged in, redirect to the login page
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
		} else {
			// Logged in, continue with the filter chain
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

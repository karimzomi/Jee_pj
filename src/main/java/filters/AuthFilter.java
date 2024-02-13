package filters;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public AuthFilter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);

		// Check if the user is logged in
		if (session == null || session.getAttribute("email") == null || session.getAttribute("id") == null) {
			// Not logged in, redirect to the login page
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.jsp");
		} else {
			// Logged in, continue with the filter chain
			request.setAttribute("id", session.getAttribute("id"));
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

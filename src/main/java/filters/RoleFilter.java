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

import java.io.IOException;

@WebFilter(urlPatterns = { "/users", "/users.jsp" }, filterName = "RoleFilter")
public class RoleFilter extends HttpFilter {

	private static final long serialVersionUID = 1L;

	public RoleFilter() {
		super();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = httpRequest.getSession(false);
        if(session.getAttribute("role").equals("admin")) {
            chain.doFilter(request, response);
        } else {
            httpResponse.sendError(403);
        }
        
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

}

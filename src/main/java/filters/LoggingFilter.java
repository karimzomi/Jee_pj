package filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import models.UserBean;
import wrapper.LoggingRequestWrapper;

import java.io.IOException;

@WebFilter(urlPatterns = { "*" }, filterName = "LoggingFilter")
public class LoggingFilter extends HttpFilter {

    private static final long serialVersionUID = 1L;

    public LoggingFilter() {
        super();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession(false);

        LoggingRequestWrapper requestWrapper = new LoggingRequestWrapper(httpRequest);
        requestWrapper.logRequest();

        if(session != null) {
            UserBean user = (UserBean) session.getAttribute("user");
            System.out.println(user.getEmail() + " is trying to access " + httpRequest.getRequestURI() + " page") ;
        }
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}

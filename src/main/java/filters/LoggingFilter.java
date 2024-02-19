package filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
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

        LoggingRequestWrapper requestWrapper = new LoggingRequestWrapper(httpRequest);
        requestWrapper.logRequest();
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

}

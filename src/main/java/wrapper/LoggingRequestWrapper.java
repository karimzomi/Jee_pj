package wrapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;

public class LoggingRequestWrapper extends HttpServletRequestWrapper {
    private HttpServletRequest request;
    public LoggingRequestWrapper(HttpServletRequest request) {
        super(request);
        this.request = request;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        // Log the parameter and its value
        System.out.println("Parameter " + name + " = " + value);
        return value;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Enumeration<String> parameterNames = super.getParameterNames();
        // Log all parameter names
        System.out.println("Request Parameters:");
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            System.out.println(paramName + " = " + getParameter(paramName));
        }
        return Collections.enumeration(Collections.list(parameterNames));
    }

    public void logRequest() {
        String ipAddress = request.getRemoteAddr();
        String method = request.getMethod();
        System.out.println(new Date().toString() + ":  IP:" + ipAddress + " did a " + method + " request");
    }
}
package filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LocaleFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;

        if(req.getParameter("sessionLocale") != null) req.getSession().setAttribute("lang", req.getParameter("sessionLocale"));
        if(req.getParameter("sessionLocale") == null && req.getSession().getAttribute("lang") == null)
            req.getSession().setAttribute("lang","en");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}

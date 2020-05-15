package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AuthorizationFilter implements Filter {

    public AuthorizationFilter(){}

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession session = req.getSession(false);

            String reqURI = req.getRequestURI();
            if (reqURI.indexOf("/login.xhtml") >= 0
                    || (session != null && session.getAttribute("username") != null)
                    || reqURI.contains("javax.faces.resource"))
                filterChain.doFilter(request, response);
            else
                resp.sendRedirect(req.getContextPath() + "/login.xhtml");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }
}

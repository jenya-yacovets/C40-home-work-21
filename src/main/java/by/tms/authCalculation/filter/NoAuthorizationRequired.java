package by.tms.authCalculation.filter;

import by.tms.authCalculation.config.TypeMessageEnum;
import by.tms.authCalculation.entity.FrontMessage;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"AuthorizationServlet", "RegisterServlet"})
public class NoAuthorizationRequired extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        if(req.getSession().getAttribute("user") == null) {
            chain.doFilter(req, res);
        } else {
            req.getSession().setAttribute("message", new FrontMessage(TypeMessageEnum.WARN, "Вы уже авторизованы"));
            res.sendRedirect("/");
        }
    }
}

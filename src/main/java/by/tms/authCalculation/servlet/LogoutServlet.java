package by.tms.authCalculation.servlet;

import by.tms.authCalculation.config.TypeMessageEnum;
import by.tms.authCalculation.entity.FrontMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/logout", name = "LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("user");
        req.getSession().setAttribute("message", new FrontMessage(TypeMessageEnum.INFO, "Вы успешно вышли из аккаунта"));
        resp.sendRedirect("/authorization");
    }
}

package by.tms.authCalculation.servlet;

import by.tms.authCalculation.config.TypeMessageEnum;
import by.tms.authCalculation.entity.FrontMessage;
import by.tms.authCalculation.entity.User;
import by.tms.authCalculation.exception.ParametersNotPassedException;
import by.tms.authCalculation.exception.UserNotFoundException;
import by.tms.authCalculation.servise.UserService;
import by.tms.authCalculation.util.Servlets;
import by.tms.authCalculation.util.Validation;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/authorization", name = "AuthorizationServlet")
public class AuthorizationServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Servlets.messageMoving(req);
        getServletContext().getRequestDispatcher("/authorization.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        FrontMessage message;

        String login = req.getParameter("login");
        String password = req.getParameter("password");

        try {
            User userAuth = userService.getUser(Validation.authorization(login, password));
            message = new FrontMessage(TypeMessageEnum.INFO, "Вы успешно аторизовались");
            req.getSession().setAttribute("user", userAuth);
        } catch (UserNotFoundException e) {
            message = new FrontMessage(TypeMessageEnum.WARN, "Логин или пароль введен не верно");
        } catch (ParametersNotPassedException e) {
            message = new FrontMessage(TypeMessageEnum.WARN, "Не переданы обязательные параметры");
        }

        req.getSession().setAttribute("message", message);
        resp.sendRedirect("/");
    }
}

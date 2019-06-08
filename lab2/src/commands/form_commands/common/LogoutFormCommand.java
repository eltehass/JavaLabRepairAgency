package commands.form_commands.common;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutFormCommand extends BaseFormCommand {

    public LogoutFormCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        super(request, response, context);
    }

    @Override
    public void execute() throws ServletException, IOException {
        response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
        response.setHeader("Progma","no-cache");
        response.setDateHeader("Expires", 0);

        request.getSession().removeAttribute("user");
        request.getSession(false);
        request.getSession().setAttribute("user", null);
        request.getSession().invalidate();

        RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);
    }
}

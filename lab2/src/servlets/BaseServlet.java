package servlets;

import commands.FormCommandFactory;
import commands.form_commands.common.BaseFormCommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "servlets.BaseServlet", urlPatterns = "/BasePage")
public class BaseServlet extends HttpServlet {

    void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var formCommandFactory = new FormCommandFactory(request, response, getServletContext());
        var command = request.getParameter("command");

        BaseFormCommand formCommand;
        if (command == null || formCommandFactory.getCommand(command) == null) {
            formCommand = new BaseFormCommand(request,response,getServletContext()) {
                @Override
                public void execute() throws ServletException, IOException {
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                }
            };
        } else {
            formCommand = formCommandFactory.getCommand(command);
        }

        formCommand.execute();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    //protected abstract String defaultPage();

}

package commands.form_commands.manager;

import commands.form_commands.common.BaseFormCommand;
import commands.person_commands.manager.AcceptCommand;
import model.extra.Application;
import model.roles.BasePerson;
import model.roles.Manager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class AcceptFormCommand extends BaseFormCommand {
    public AcceptFormCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        super(request, response, context);
    }

    @Override
    public void execute() throws ServletException, IOException {
        List<Application> unhandledApplications = new ArrayList<>();
        List<BasePerson> masters = new ArrayList<>();

        try {
            unhandledApplications = dbProvider.applicationDao().getAllNotHandled();
            masters = dbProvider.personInfoDao().getAllMasters();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("unhandled_applications", unhandledApplications);
        request.setAttribute("masters", masters);

        var user = (Manager) request.getSession().getAttribute("user");

        request.setAttribute("usrName", user.name);
        request.setAttribute("usrSurname", user.surname);

        var appStr = request.getParameter("managerInputAppId");
        var masterStr = request.getParameter("managerInputMasterId");
        var priceStr = request.getParameter("managerInputPrice");


        if (appStr == null || masterStr == null || priceStr == null || appStr.isEmpty() || masterStr.isEmpty() || priceStr.isEmpty()) {
            request.setAttribute("message", "Error: one or some of fields: appId, masterId and price are empty");
            RequestDispatcher dispatcher = context.getRequestDispatcher("/home_manager.jsp");
            dispatcher.forward(request, response);
            return;
        }

        var appId = Integer.parseInt(appStr);
        var masterId = Integer.parseInt(masterStr);
        var price = Integer.parseInt(priceStr);

        userCommand = new AcceptCommand(user, appId, masterId, price);
        try {
            if (!unhandledApplications.stream().map(app -> app.id).collect(Collectors.toList()).contains(appId)) {
                request.setAttribute("message", "You can't accept app with id = " + appId + ", because it was handled before or doesn't exists");
                RequestDispatcher dispatcher = context.getRequestDispatcher("/home_manager.jsp");
                dispatcher.forward(request, response);
                return;
            }

            userCommand.execute();

            try {
                unhandledApplications = dbProvider.applicationDao().getAllNotHandled();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("unhandled_applications", unhandledApplications);
            request.setAttribute("message", "Application with id = " + appId + " was accepted with price = " + price);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: try correct appId, masterId and price");
        }

        RequestDispatcher dispatcher = context.getRequestDispatcher("/home_manager.jsp");
        dispatcher.forward(request, response);
    }

}

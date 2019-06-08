package commands.form_commands.customer;

import commands.form_commands.common.BaseFormCommand;
import commands.person_commands.customer.CreateCommand;
import model.extra.Application;
import model.extra.Invoice;
import model.extra.RegectedApplication;
import model.roles.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class CreateFormCommand extends BaseFormCommand {
    public CreateFormCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        super(request, response, context);
    }

    @Override
    public void execute() throws ServletException, IOException {
        var user = (Customer) request.getSession().getAttribute("user");

        List<Invoice> invoices = new ArrayList<>();
        List<RegectedApplication> regectedApplications = new ArrayList<>();

        try {
            invoices = dbProvider.invoiceDao().getFinishedForCustomer(user.id);
            regectedApplications = dbProvider.regectedApplicationsDao().getAllForCustomer(user.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("usrName", user.name);
        request.setAttribute("usrSurname", user.surname);

        request.setAttribute("finished_invoices", invoices);
        request.setAttribute("rejected_applications", regectedApplications);

        var body = request.getParameter("customerInputBody");

        if (!body.isEmpty()) {
            userCommand = new CreateCommand(user, body);
            try {
                userCommand.execute();
                request.setAttribute("message", "Application was created with body = " + body);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Error: try correct body");
            }
        } else {
            request.setAttribute("message", "Incorrect input data for Create action");
        }

        RequestDispatcher dispatcher = context.getRequestDispatcher("/home_customer.jsp");
        dispatcher.forward(request, response);
    }

}

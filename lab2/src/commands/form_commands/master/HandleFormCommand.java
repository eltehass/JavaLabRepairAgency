package commands.form_commands.master;

import commands.form_commands.common.BaseFormCommand;
import commands.person_commands.master.HandleCommand;
import model.extra.Invoice;
import model.extra.UserResponse;
import model.roles.Master;

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
public class HandleFormCommand extends BaseFormCommand {
    public HandleFormCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        super(request, response, context);
    }

    @Override
    public void execute() throws ServletException, IOException {
        var user = (Master) request.getSession().getAttribute("user");

        List<Invoice> undoneInvoices = new ArrayList<>();
        List<UserResponse> userResponses = new ArrayList<>();

        try {
            undoneInvoices = dbProvider.invoiceDao().getNotDoneForMaster(user.id);
            userResponses = dbProvider.userResponsesDao().getAllForMaster(user.id);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("undone_invoices", undoneInvoices);
        request.setAttribute("user_responses", userResponses);

        request.setAttribute("usrName", user.name);
        request.setAttribute("usrSurname", user.surname);

        var invoiceStr = request.getParameter("inputInvoiceId");

        if (invoiceStr == null || invoiceStr.isEmpty()) {
            request.setAttribute("message", "Error: invoiceId field is empty");
            RequestDispatcher dispatcher = context.getRequestDispatcher("/home_master.jsp");
            dispatcher.forward(request, response);
            return;
        }

        var invoiceId = Integer.parseInt(invoiceStr);

        userCommand = new HandleCommand(user, invoiceId);
        if (!undoneInvoices.stream().map(invoice -> invoice.id).collect(Collectors.toList()).contains(invoiceId)) {
            request.setAttribute("message", "You can't handle invoice with id = " + invoiceId + ", because it was handled before, or it was set for another master, or doesn't exists");
            RequestDispatcher dispatcher = context.getRequestDispatcher("/home_master.jsp");
            dispatcher.forward(request, response);
            return;
        }

        try {
            userCommand.execute();

            try {
                undoneInvoices = dbProvider.invoiceDao().getNotDoneForMaster(user.id);
                userResponses = dbProvider.userResponsesDao().getAllForMaster(user.id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            request.setAttribute("undone_invoices", undoneInvoices);
            request.setAttribute("user_responses", userResponses);
            request.setAttribute("message", "Invoice with id = " + invoiceId + " was handled");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error: try correct invoiceId");
        }

        RequestDispatcher dispatcher = context.getRequestDispatcher("/home_master.jsp");
        dispatcher.forward(request, response);
    }

}
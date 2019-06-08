package commands.form_commands.customer;

import commands.form_commands.common.BaseFormCommand;
import commands.person_commands.customer.CommentCommand;
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
import java.util.stream.Collectors;

@SuppressWarnings("Duplicates")
public class CommentFormCommand extends BaseFormCommand {
    public CommentFormCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
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


        var invoiceStr = request.getParameter("customerInputInvoiceId");
        var comment = request.getParameter("customerInputComment");

        if (!comment.isEmpty() && invoiceStr != null && !invoiceStr.isEmpty()) {
            var invoiceId = Integer.parseInt(invoiceStr);
            if (!invoices.stream().map(invoice -> invoice.id).collect(Collectors.toList()).contains(invoiceId)) {
                request.setAttribute("message", "Write finished invoiceId with your appId, because invoice with id = " + invoiceId + " isn't finished, not yours or doesn't exists");
                RequestDispatcher dispatcher = context.getRequestDispatcher("/home_customer.jsp");
                dispatcher.forward(request, response);
                return;
            }

            userCommand = new CommentCommand(user, invoiceId, comment);
            try {
                userCommand.execute();
                request.setAttribute("message", "Invoice with id = " + invoiceId + " got comment: " + comment);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Error: try correct invoiceId");
            }
        } else {
            request.setAttribute("message", "Incorrect input data for Comment action");
        }

        RequestDispatcher dispatcher = context.getRequestDispatcher("/home_customer.jsp");
        dispatcher.forward(request, response);
    }

}
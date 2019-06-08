package commands.form_commands.common;

import extra.HPage;
import model.db.dao.PersonInfoDao;
import model.extra.Application;
import model.extra.Invoice;
import model.extra.RegectedApplication;
import model.extra.UserResponse;
import model.roles.BasePerson;
import model.roles.PersonType;

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
public class LoginFormCommand extends BaseFormCommand {

    public LoginFormCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        super(request, response, context);
    }

    @Override
    public void execute() throws ServletException, IOException {
        var userLogin = request.getParameter("signInLoginInput");
        var userPass = request.getParameter("signInPasswordInput");

        if (!userLogin.isEmpty() && !userPass.isEmpty()) {
            BasePerson user = null;
            try {
                user = dbProvider.personInfoDao().logIn(userLogin, userPass);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Incorrect login and password");
                RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (user != null) {
                request.setAttribute("usrName", user.name);
                request.setAttribute("usrSurname", user.surname);

                PersonType userType = null;
                try {
                    userType = dbProvider.personTypeDao().getEntityById(user.idPersonType);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Incorrect UserType");
                    RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                    return;
                }

                var userPageLink = "";
                switch (userType) {
                    case CUSTOMER: {
                        userPageLink = "/home_customer.jsp";

                        List<Invoice> invoices = new ArrayList<>();
                        List<RegectedApplication> regectedApplications = new ArrayList<>();

                        try {
                            invoices = dbProvider.invoiceDao().getFinishedForCustomer(user.id);
                            regectedApplications = dbProvider.regectedApplicationsDao().getAllForCustomer(user.id);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        request.setAttribute("finished_invoices", invoices);
                        request.setAttribute("rejected_applications", regectedApplications);

                        break;
                    }

                    case MANAGER: {
                        userPageLink = "/home_manager.jsp";

                        List<Application> applications = new ArrayList<>();
                        List<BasePerson> masters = new ArrayList<>();

                        try {
                            applications = dbProvider.applicationDao().getAllNotHandled();
                            masters = dbProvider.personInfoDao().getAllMasters();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        request.setAttribute("unhandled_applications", applications);
                        request.setAttribute("masters", masters);

                        break;
                    }

                    case MASTER: {
                        userPageLink = "/home_master.jsp";

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

                        break;
                    }
                }

                HPage.CURRENT_HOME_PAGE = userPageLink;

                request.getSession().setAttribute("user", user);
                System.out.print("USER ID = " + user.id);

                RequestDispatcher dispatcher = context.getRequestDispatcher(userPageLink);
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "Incorrect login and password");
                RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("message", "One or two fields are empty");
            RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}

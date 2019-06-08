package commands.form_commands.common;

import model.roles.BasePerson;
import model.roles.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@SuppressWarnings("Duplicates")
public class RegisterFormCommand extends BaseFormCommand {

    public RegisterFormCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        super(request, response, context);
    }

    @Override
    public void execute() throws ServletException, IOException {
        var userLogin = request.getParameter("signUpLoginInput");
        var userPass = request.getParameter("signUpPasswordInput");
        var userRepeatPass = request.getParameter("signUpRepeatPasswordInput");
        var userName = request.getParameter("signUpName");
        var userSurname = request.getParameter("signUpSurname");
        var userAge = request.getParameter("signUpAge");
        var userEmail = request.getParameter("signUpEmail");
        var userPhone = request.getParameter("signUpPhone");

        if (!userLogin.isEmpty() && !userPass.isEmpty() && !userRepeatPass.isEmpty() && !userName.isEmpty() && !userSurname.isEmpty() && !userAge.isEmpty() && !userEmail.isEmpty() && !userPhone.isEmpty() && userPass.equals(userRepeatPass)) {
            BasePerson user = null;
            try {
                user = dbProvider.personInfoDao().checkIfExist(userLogin, userPass, userName, userSurname);
            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("message", "Incorrect input data while registration");
                RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
                return;
            }

            if (user == null) {

                BasePerson newUser = new Customer(userName, userSurname, Integer.parseInt(userAge), userLogin, userPass, userEmail, userPhone);

                try {
                    dbProvider.personInfoDao().create(newUser);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Trouble with creating new user, try again with correct data");
                    RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request, response);
                    return;
                }
                request.setAttribute("message", "Success registration, now try to login");
                RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("message", "Error: user with such credentials exists");
                RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("message", "Incorrect data while registration");
            RequestDispatcher dispatcher = context.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}

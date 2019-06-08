package commands.form_commands.common;

import commands.person_commands.base.BaseCommand;
import model.db.DBProvider;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class BaseFormCommand {

    public final HttpServletRequest request;
    public final HttpServletResponse response;
    public final ServletContext context;
    public final DBProvider dbProvider;
    public BaseCommand userCommand;

    public BaseFormCommand(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        this.request = request;
        this.response = response;
        this.context = context;
        this.dbProvider = new DBProvider();
    }

    public abstract void execute() throws ServletException, IOException;

}

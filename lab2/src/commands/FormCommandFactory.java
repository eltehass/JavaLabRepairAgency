package commands;

import commands.form_commands.common.BaseFormCommand;
import commands.form_commands.common.LoginFormCommand;
import commands.form_commands.common.LogoutFormCommand;
import commands.form_commands.common.RegisterFormCommand;
import commands.form_commands.customer.CommentFormCommand;
import commands.form_commands.customer.CreateFormCommand;
import commands.form_commands.manager.AcceptFormCommand;
import commands.form_commands.manager.UnacceptFormCommand;
import commands.form_commands.master.HandleFormCommand;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class FormCommandFactory {
    public final Map<String, BaseFormCommand> commands = new HashMap<>();

    public FormCommandFactory(HttpServletRequest request, HttpServletResponse response, ServletContext context) {
        commands.put("loginCommand",    new LoginFormCommand(request,response,context));
        commands.put("registerCommand", new RegisterFormCommand(request,response,context));
        commands.put("commentCommand",  new CommentFormCommand(request,response,context));
        commands.put("createCommand",   new CreateFormCommand(request,response,context));
        commands.put("acceptCommand",   new AcceptFormCommand(request,response,context));
        commands.put("declineCommand",  new UnacceptFormCommand(request,response,context));
        commands.put("handleCommand",   new HandleFormCommand(request,response,context));
        commands.put("logoutCommand",   new LogoutFormCommand(request,response,context));
    }

    public BaseFormCommand getCommand(String command){
        return commands.getOrDefault(command, null);
    }
}

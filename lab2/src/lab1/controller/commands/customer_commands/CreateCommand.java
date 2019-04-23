package lab1.controller.commands.customer_commands;

import lab1.controller.commands.base.BaseCommand;
import lab1.model.db.DBProvider;
import lab1.model.roles.Customer;

public class CreateCommand extends BaseCommand {

    String message;

    public CreateCommand(String message) {
        super(DBProvider.personInfoDao.findEntityById(0));
        this.message = message;
    }

    @Override
    public String execute() {
        return "App was created with uuid = " + ((Customer) person).createApplication(message).id.toString();
    }
}

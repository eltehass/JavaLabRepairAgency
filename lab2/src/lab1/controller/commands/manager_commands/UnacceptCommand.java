package lab1.controller.commands.manager_commands;

import lab1.controller.commands.base.BaseCommand;
import lab1.model.db.DBProvider;
import lab1.model.roles.Manager;

import java.util.UUID;

public class UnacceptCommand extends BaseCommand {

    UUID id;
    boolean isAccepted;
    int price;
    String explanation;

    public UnacceptCommand(UUID id, boolean isAccepted, int price, String explanation) {
        super(DBProvider.personInfoDao.findEntityById(0));
        this.id = id;
        this.isAccepted = isAccepted;
        this.price = price;
        this.explanation = explanation;
    }

    @Override
    public String execute() {
        return ((Manager) person).processApplication(id, isAccepted, price, explanation);
    }

}

package lab1.controller.commands.master_commands;

import lab1.controller.commands.base.BaseCommand;
import lab1.model.db.DBProvider;
import lab1.model.roles.Master;

import java.util.UUID;

public class HandleCommand extends BaseCommand {

    UUID id;

    public HandleCommand(UUID id) {
        super(DBProvider.personInfoDao.findEntityById(0));
        this.id = id;
    }

    @Override
    public String execute() {
        return ((Master) person).makeRepairing(id);
    }
}

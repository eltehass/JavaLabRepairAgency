package lab1.controller.commands.base;

import lab1.model.extra.RepairAgency;
import lab1.model.roles.BasePerson;

public abstract class BaseCommand implements Command {

    protected RepairAgency agency;
    protected BasePerson person;

    public BaseCommand(BasePerson basePerson) {
        agency = RepairAgency.getInstance();
        person = basePerson;
    }

}
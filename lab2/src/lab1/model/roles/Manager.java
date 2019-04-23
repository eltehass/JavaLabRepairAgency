package lab1.model.roles;

import lab1.model.extra.RepairAgency;
import java.util.UUID;

public class Manager extends BasePerson {

    public Manager() {
        super(PersonType.MANAGER);
    }

    public Manager(String name, String surname, int age) {
        super(name, surname, age, PersonType.MANAGER);
    }

    public String processApplication(UUID id, boolean isAccepted, int price, String explanation) {
        var app = RepairAgency.getInstance().getAppByUUID(id);

        if (app == null) {
            return "NOT_FOUND";
        }

        if (isAccepted) {
            RepairAgency.getInstance().addInvoice(price, id);
            RepairAgency.getInstance().removeApp(id);
            return "ACCEPTED";
        } else {
            return "UNACCEPTED:, because:  " + explanation;
        }
    }

}
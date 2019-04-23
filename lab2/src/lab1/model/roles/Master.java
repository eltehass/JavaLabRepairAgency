package lab1.model.roles;

import lab1.model.extra.RepairAgency;
import java.util.UUID;

public class Master extends BasePerson {

    public Master() {
        super(PersonType.MASTER);
    }

    public Master(String name, String surname, int age) {
        super(name, surname, age, PersonType.MASTER);
    }

    public String makeRepairing(UUID id) {
        return RepairAgency.getInstance().handleInvoice(id);
    }

}
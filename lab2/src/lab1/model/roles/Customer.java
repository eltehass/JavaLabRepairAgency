package lab1.model.roles;

import lab1.model.extra.Application;
import lab1.model.extra.RepairAgency;

public class Customer extends BasePerson {

    public Customer() {
        super(PersonType.CUSTOMER);
    }

    public Customer(String name, String surname, int age) {
        super(name, surname, age, PersonType.CUSTOMER);
    }

    public Application createApplication(String appBody) {
        var app = new Application(appBody);
        RepairAgency.getInstance().addApp(app);
        return app;
    }

}
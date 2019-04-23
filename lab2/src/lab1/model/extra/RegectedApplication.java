package lab1.model.extra;

import lab1.model.roles.Manager;

import java.util.UUID;

public class RegectedApplication {
    public final UUID id;
    public final Application application;
    public final Manager manager;
    public final String message;

    public RegectedApplication(UUID id, Application application, Manager manager, String message) {
        this.id = id;
        this.application = application;
        this.manager = manager;
        this.message = message;
    }
}

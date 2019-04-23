package lab1.model.extra;

import java.util.UUID;

public class Application {
    public final UUID id;
    public final String applicationBody;
    public Application(String applicationBody) {
        this.id = UUID.randomUUID();
        this.applicationBody = applicationBody;
    }
}
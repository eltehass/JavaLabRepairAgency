package lab1.model.extra;

import java.util.UUID;

public class UserResponse {
    public final UUID id;
    public final Invoice invoice;
    public final String comment;

    public UserResponse(UUID id, Invoice invoice, String comment) {
        this.id = id;
        this.invoice = invoice;
        this.comment = comment;
    }
}

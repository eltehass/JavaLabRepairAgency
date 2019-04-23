package lab1.model.extra;

import java.util.UUID;

public class Invoice {
    public final UUID id;
    public final Application app;
    public final int price;
    public String userComment = "";
    public boolean isDone = false;

    public Invoice(int price, Application app) {
        this.id = app.id;
        this.price = price;
        this.app = app;
    }

    public void setUserComment(String userComment) {
        this.userComment = userComment;
    }

    public void setProgress(boolean isDone) {
        this.isDone = isDone;
    }

}
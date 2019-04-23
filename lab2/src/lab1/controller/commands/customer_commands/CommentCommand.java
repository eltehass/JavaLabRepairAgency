package lab1.controller.commands.customer_commands;

import lab1.controller.commands.base.BaseCommand;
import lab1.model.db.DBProvider;

import java.util.UUID;

public class CommentCommand extends BaseCommand {

    UUID id;
    String comment;

    public CommentCommand(UUID id, String comment) {
        super(DBProvider.personInfoDao.findEntityById(0));
        this.id = id;
        this.comment = comment;
    }

    @Override
    public String execute() {
        return agency.setInvoiceUserComment(id, comment);
    }
}

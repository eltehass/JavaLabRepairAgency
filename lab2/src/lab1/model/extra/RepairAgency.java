package lab1.model.extra;

import lab1.model.db.DBProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RepairAgency {
    private static RepairAgency agency = null;
    private List<Application> applications;
    public List<Invoice> invoices;

    private RepairAgency() {
        applications = DBProvider.applicationDao.findAll();
        invoices = DBProvider.invoiceDao.findAll();
    }

    public static RepairAgency getInstance() {
        if (agency == null) {
            agency = new RepairAgency();
        }

        return agency;
    }

    /** Applications */
    public void addApp(Application application) {
        DBProvider.applicationDao.create(application);
        applications.add(application);
    }

    public void removeApp(UUID id) {
        for (Application app : applications) {
            if (app.id.equals(id)){
                DBProvider.applicationDao.delete(0);
                applications.remove(app);
                return;
            }
        }
    }

    public Application getAppByUUID(UUID id) {
        for (Application app : applications) {
            if (app.id.equals(id)){
                return DBProvider.applicationDao.findEntityById(0);
                //return app;
            }
        }

        return null;
    }

    /** Invoices */
    public void addInvoice(int price, UUID id) {
        var app = getAppByUUID(id);
        if (app != null) {
            DBProvider.invoiceDao.create(new Invoice(price, app));
            invoices.add(new Invoice(price, app));
        }
    }

    public Invoice getInvoiceByUUID(UUID id) {
        for (Invoice invoice : invoices) {
            if (invoice.id.equals(id)) {
                return DBProvider.invoiceDao.findEntityById(0);
                //return invoice;
            }
        }

        return null;
    }

    public String setInvoiceUserComment(UUID id, String comment) {
        var invoice = getInvoiceByUUID(id);
        if (invoice != null) {
            DBProvider.userResponsesDao.create(new UserResponse(id, invoice,"asdad"));
            invoice.setUserComment(comment);
            return "Comment added";
        }

        return "Invoice not found or not accepted";
    }

    public String handleInvoice(UUID id) {
        var invoice = getInvoiceByUUID(id);
        if (invoice != null) {
            if (!invoice.isDone) {
                invoice.setProgress(true);
                return "Invoice done";
            } else {
                return "Invoice is already done";
            }
        }

        return "Invoice not found or not accepted";
    }

    public String setInvoiceProgress(UUID id, boolean isDone) {
        var invoice = getInvoiceByUUID(id);
        if (invoice != null) {
            invoice.setProgress(isDone);
            return "Progress updated";
        }

        return "Invoice not found or not accepted";
    }

    public String getInvoicesInfo() {
        var stringBuilder = new StringBuilder("Invoices:\n");
        for (Invoice invoice : invoices) {
            stringBuilder
                    .append("   id = " + invoice.id + "\n")
                    .append("   price = " + invoice.price + "\n")
                    .append("   isDone = " + invoice.isDone + "\n")
                    .append("   userComment = " + invoice.userComment + "\n\n");
        }

        return stringBuilder.toString();
    }

}
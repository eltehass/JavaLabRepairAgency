package lab1.model.db;

import lab1.model.db.dao.*;

public class DBProvider {
    public final static ApplicationDao applicationDao = new ApplicationDao();
    public final static InvoiceDao invoiceDao = new InvoiceDao();
    public final static PersonInfoDao personInfoDao = new PersonInfoDao();
    public final static PersonTypeDao personTypeDao = new PersonTypeDao();
    public final static RegectedApplicationsDao regectedApplicationsDao = new RegectedApplicationsDao();
    public final static UserResponsesDao userResponsesDao = new UserResponsesDao();
}

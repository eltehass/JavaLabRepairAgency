package lab1.model.db.dao;

import lab1.model.extra.Invoice;

import java.util.List;

public class InvoiceDao extends BaseDao<Integer, Invoice> {
    @Override
    public List<Invoice> findAll() {
        return null;
    }

    @Override
    public Invoice findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Invoice id) {
        return false;
    }

    @Override
    public Invoice update(Invoice id) {
        return null;
    }
}

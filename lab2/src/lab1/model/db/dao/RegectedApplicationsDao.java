package lab1.model.db.dao;

import lab1.model.extra.RegectedApplication;

import java.util.List;

public class RegectedApplicationsDao extends BaseDao<Integer, RegectedApplication> {
    @Override
    public List<RegectedApplication> findAll() {
        return null;
    }

    @Override
    public RegectedApplication findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(RegectedApplication id) {
        return false;
    }

    @Override
    public RegectedApplication update(RegectedApplication id) {
        return null;
    }
}

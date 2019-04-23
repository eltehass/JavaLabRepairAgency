package lab1.model.db.dao;

import lab1.model.extra.Application;

import java.util.List;

public class ApplicationDao extends BaseDao<Integer, Application> {
    @Override
    public List<Application> findAll() {
        return null;
    }

    @Override
    public Application findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(Application id) {
        return false;
    }

    @Override
    public Application update(Application id) {
        return null;
    }
}

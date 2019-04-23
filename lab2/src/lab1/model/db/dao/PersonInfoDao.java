package lab1.model.db.dao;

import lab1.model.roles.BasePerson;

import java.util.List;

public class PersonInfoDao extends BaseDao<Integer, BasePerson> {
    @Override
    public List<BasePerson> findAll() {
        return null;
    }

    @Override
    public BasePerson findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(BasePerson id) {
        return false;
    }

    @Override
    public BasePerson update(BasePerson id) {
        return null;
    }
}

package lab1.model.db.dao;

import lab1.model.roles.PersonType;

import java.util.List;

public class PersonTypeDao extends BaseDao<Integer, PersonType> {
    @Override
    public List<PersonType> findAll() {
        return null;
    }

    @Override
    public PersonType findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(PersonType id) {
        return false;
    }

    @Override
    public PersonType update(PersonType id) {
        return null;
    }
}

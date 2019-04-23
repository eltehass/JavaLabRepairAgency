package lab1.model.db.dao;

import java.util.List;

public abstract class BaseDao <Key, Type> {
    public abstract List<Type> findAll();
    public abstract Type findEntityById(Key id);
    public abstract boolean delete(Key id);
    public abstract boolean create(Type id);
    public abstract Type update(Type id);
}
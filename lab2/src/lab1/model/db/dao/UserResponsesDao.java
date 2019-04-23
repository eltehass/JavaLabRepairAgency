package lab1.model.db.dao;

import lab1.model.extra.UserResponse;

import java.util.List;

public class UserResponsesDao extends BaseDao<Integer, UserResponse> {
    @Override
    public List<UserResponse> findAll() {
        return null;
    }

    @Override
    public UserResponse findEntityById(Integer id) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public boolean create(UserResponse id) {
        return false;
    }

    @Override
    public UserResponse update(UserResponse id) {
        return null;
    }
}

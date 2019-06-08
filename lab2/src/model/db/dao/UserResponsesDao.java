package model.db.dao;


import model.extra.UserResponse;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
public class UserResponsesDao extends AbstractDao<UserResponse, Integer> {

    public UserResponsesDao(Connection connection) { super(connection); }

    @Override
    public List<UserResponse> getAll() throws SQLException {
        var preparedStatement = getPrepareStatement("SELECT * FROM user_responses");
        var result = new ArrayList<UserResponse>();

        try {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getInt(1);
                var id_invoice = resultSet.getInt(2);
                var comment = resultSet.getString(3);
                result.add(new UserResponse(id, id_invoice, comment));
            }
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return result;
    }

    public List<UserResponse> getAllForMaster(Integer masterId) throws SQLException {
        var preparedStatement = getPrepareStatement("SELECT * FROM user_responses WHERE (id_invoice in (SELECT id FROM invoices WHERE id_master = ?))");
        var result = new ArrayList<model.extra.UserResponse>();

        try {
            preparedStatement.setInt(1, masterId);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getInt(1);
                var id_invoice = resultSet.getInt(2);
                var comment = resultSet.getString(3);
                result.add(new model.extra.UserResponse(id, id_invoice, comment));
            }
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return result;
    }


    @Override
    public UserResponse getEntityById(Integer id) throws SQLException {
        var preparedStatement = getPrepareStatement("SELECT * FROM user_responses WHERE id = ?");
        UserResponse response = null;

        try {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                var id_invoice = resultSet.getInt(2);
                var comment = resultSet.getString(3);
                response = new UserResponse(id, id_invoice, comment);
            }
        } finally {
            closePrepareStatement(preparedStatement);
        }

        return response;
    }

    @Override
    public boolean update(Integer id, UserResponse entity) throws SQLException {
        return handleQuery(
                "UPDATE user_responses SET id_invoice = ?, comment = ? WHERE id = ?" ,
                entity.idInvoice, entity.comment, id
        );
    }

    @Override
    public boolean delete(Integer id) throws SQLException {
        return handleQuery(
                "DELETE FROM user_responses WHERE id = ?",
                id
        );
    }

    @Override
    public boolean create(UserResponse entity) throws SQLException {
        return handleQuery(
                "INSERT INTO user_responses (id_invoice, comment) VALUES (?, ?)",
                entity.idInvoice, entity.comment
        );
    }
}

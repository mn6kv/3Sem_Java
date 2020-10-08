package repositories;

import jdk.internal.jline.internal.Nullable;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UsersRepository {

    //language=SQL
    private static final String SQL_FIND_ALL_USERS = "select * from first_servlet_db";
    //language=SQL
    private static final String SQL_IS_USER_EXIST = "select exists (select * from first_servlet_db " +
            "where name = ? and password = ?)";
    //language=SQL
    private static final String SQL_SAVE_USER = "insert in first_servlet_db(name, password, age, uuid)" +
            "values(?, ?, ?, ?)";
    //language=SQL
    private static final String SQL_ADD_UUID = "update first_servlet_db set " + "uuid = ? where name = ? and password = ?";
    //language=SQL
    private static final String SQL_IS_CURRENT_UUID_EXISTS = "select exists (select * from first_servlet_db "
            + "where uuid = ?)";
    //language=SQL
    private static final String IF_USER_HAS_UUID = "select exists (select * from first_servlet_db "
            + "where name = ? and password = ? and uuid is not null)";
    //language=SQL
    private static final String SQL_GET_UUID = "select uuid from first_servlet_db "
            + "where name = ? and password = ?";

    private Connection connection;

    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> findAllByAge(Integer age) {
        return null;
    }

    @Override
    public boolean isUserExist(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_USER_EXIST);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean("exists");
            } else return false;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public boolean saveUUIDtoExistingUser(String username, String password, String uuid) {
        try {
            //"update first_servlet_db set" + "UUID = ? where name = ? and password = ?"
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_UUID);
            preparedStatement.setString(1, uuid);
            preparedStatement.setString(2, username);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(SQL_FIND_ALL_USERS);

            List<User> users = new ArrayList<>();

            while (result.next()) {
                users.add(User.builder().
                        id(result.getLong("id")).
                        name(result.getString("name")).
                        password(result.getString("password")).
                        age(result.getInt("age")).
                        build());
            }

            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User user) {
        //"insert in first_servlet_db(name, password, age, uuid)" + "values(?, ?, ?, ?)"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SAVE_USER);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, Integer.toString(user.getAge()));
            preparedStatement.setString(4, user.getUUID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(User entity) {

    }

    @Override
    public void deleteById(Long id) {

    }

    public boolean isCurrentUuidExists(String uuid) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_IS_CURRENT_UUID_EXISTS);
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getBoolean("exists");
            else return false;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean ifUserHasUuid(String username, String password) {
        //"select exists (select * from first_servlet_db "+ "where where name = ? and password = ? and uuid != 'null')"
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(IF_USER_HAS_UUID);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return resultSet.getBoolean("exists");
            else return false;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Nullable
    @Override
    public String getUsersUuid(String username, String password) {
        try {
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_UUID);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next())
            return resultSet.getString("uuid");
        else return null;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

package com.vsu.repository;

import com.vsu.entities.User;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private static final String SELECT_BY_ID_QUERY =
            "SELECT * FROM myuser WHERE id_user = ?";

    private static final String SELECT_BY_EMAIL_QUERY =
            "SELECT * FROM myuser WHERE email_user = ?";

    private static final String SELECT_ALL_USERS =
            "SELECT * FROM myuser";

    private static final String SELECT_BY_PHONE_QUERY =
            "SELECT * FROM myuser WHERE phone_user = ?";

    private static final String INSERT_QUERY =
            "INSERT INTO myuser(" +
                    "surname_user, name_user, birthday_user, phone_user, email_user, password_user) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String DELETE_QUERY_BY_PHONE =
            "DELETE FROM myuser WHERE phone_user = ?";

    private static final String DELETE_QUERY_BY_ID =
            "DELETE FROM myuser WHERE id_user = ?";

    private static final String UPDATE_QUERY =
            "UPDATE myuser " +
                    "SET surname_user=?, name_user=?, birthday_user=?, phone_user=?, email_user=?, password_user=? " +
                    "WHERE id_user = ?";

    public static final int WHERE_PARAMETER_INDEX = 7;

    private ConnectionFactory connectionFactory;

    public UserRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public List<User> getAll() throws SQLException {
        List<User> tourists = new ArrayList<>();
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                tourists.add(getUser(resultSet));
            }
        }
        return tourists;
    }
    public User selectById(Long id) throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
            return null;
        }
    }

    @NotNull
    private static User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id_user"));
        user.setName(resultSet.getString("name_user"));
        user.setSurname(resultSet.getString("surname_user"));
        user.setBirthday((resultSet.getDate("birthday_user")).toString());
        user.setEmail(resultSet.getString("email_user"));
        user.setPhone(resultSet.getString("phone_user"));
        user.setPassword(resultSet.getString("password_user"));
        return user;
    }

    public User selectByPhone(String phone) throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_PHONE_QUERY);
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
            return null;
        }
    }

    public User selectByEmail(String email) throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
            return null;
        }
    }

    public void insert(User user) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            setTourist(user, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setTourist(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getSurname());
        statement.setString(2, user.getName());
        statement.setDate(3, Date.valueOf(user.getBirthday()));
        statement.setString(4, user.getPhone());
        statement.setString(5, user.getEmail());
        statement.setString(6, user.getPassword());
    }

    public void deleteByPhone(String phone) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY_BY_PHONE);
            statement.setString(1,phone);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteById(Long id) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY_BY_ID);
            statement.setLong(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateByID(User user) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            setTourist(user,statement);
            statement.setLong(WHERE_PARAMETER_INDEX,user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

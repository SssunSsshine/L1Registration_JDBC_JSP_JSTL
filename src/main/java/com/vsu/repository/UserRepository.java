package com.vsu.repository;

import com.vsu.entities.User;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private static final String SELECT_BY_ID_QUERY =
            "SELECT * FROM myuser WHERE id_user = ?";

    private static final String SELECT_BY_EMAIL_QUERY =
            "SELECT * FROM myuser WHERE email_user = ?";
    private static final String INSERT_QUERY =
            "INSERT INTO myuser(" +
                    "surname_user, name_user, birthday_user, phone_user, email_user, password_user) " +
                    "VALUES (?, ?, ?, ?, ?, ?);";

    private static final String DELETE_QUERY_BY_ID =
            "DELETE FROM myuser WHERE id_user = ?";

    private static final String UPDATE_QUERY =
            "UPDATE myuser " +
                    "SET surname_user=?, name_user=?, birthday_user=?, phone_user=?, email_user=?, password_user=? " +
                    "WHERE id_user = ?";

    private ConnectionFactory connectionFactory;

    public UserRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public User selectById(Long id) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
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

    public User selectByEmail(String email) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_EMAIL_QUERY);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getUser(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int insert(User user) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            setUserParamstoStatement(user, statement);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countUpdate;
    }


    public int deleteById(Long id) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY_BY_ID);
            statement.setLong(1, id);
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countUpdate;
    }

    public int updateByID(User user) {
        int countUpdate = 0;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
            setUserParamstoStatement(user, statement);
            statement.setLong(7, user.getId());
            countUpdate = statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countUpdate;
    }

    private void setUserParamstoStatement(User user, PreparedStatement statement) throws SQLException {
        statement.setString(1, user.getSurname());
        statement.setString(2, user.getName());
        statement.setDate(3, Date.valueOf(user.getBirthday()));
        statement.setString(4, user.getPhone());
        statement.setString(5, user.getEmail());
        statement.setString(6, user.getPassword());
    }
}

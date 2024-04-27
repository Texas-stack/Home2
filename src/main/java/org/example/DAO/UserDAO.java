package org.example.DAO;

import org.example.DTO.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;

    }

    public UserDTO findById(Long id) {
        UserDTO user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                user = new UserDTO(id, resultSet.getString("username"), resultSet.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void update(UserDTO user) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE users SET username = ?, email = ? WHERE id = ?");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long createUser(UserDTO user) {
        Long userId = null;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO users (id, username, email) VALUES (DEFAULT, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getEmail());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                userId = generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return userId;
    }

    public List<UserDTO> getAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                UserDTO user = new UserDTO(resultSet.getLong("id"), resultSet.getString("username"), resultSet.getString("email"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }



}
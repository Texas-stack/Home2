package org.example.DAO;

import org.example.DTO.CategoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class  CategoryDAO {
    private Connection connection;

    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean save(CategoryDTO category) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO categories (name) VALUES (?)");
            statement.setString(1, category.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public CategoryDTO findById(Long id) {
        CategoryDTO category = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                category = new CategoryDTO(resultSet.getLong("id"), resultSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    public void update(CategoryDTO category) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE categories SET name = ? WHERE id = ?");
            statement.setString(1, category.getName());
            statement.setLong(2, category.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM categories WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CategoryDTO> getAllCategories() {
        List<CategoryDTO> categories = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM categories");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                CategoryDTO category = new CategoryDTO(resultSet.getLong("id"), resultSet.getString("name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
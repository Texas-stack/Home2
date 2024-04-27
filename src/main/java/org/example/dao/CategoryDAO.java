package org.example.dao;

import org.example.dto.CategoryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) класс для работы с категориями (CategoryDTO) в базе данных.
 */
public class CategoryDAO {
    private Connection connection;

    /**
     * Конструктор класса CategoryDAO.
     *
     * @param connection объект Connection для работы с базой данных
     */
    public CategoryDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Сохраняет категорию в базе данных.
     *
     * @param category объект CategoryDTO для сохранения
     * @return true, если категория успешно сохранена, иначе false
     */
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

    /**
     * Находит категорию по ее идентификатору в базе данных.
     *
     * @param id идентификатор категории
     * @return объект CategoryDTO, соответствующий указанному идентификатору, или null, если категория не найдена
     */
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

    /**
     * Обновляет информацию о категории в базе данных.
     *
     * @param category объект CategoryDTO с обновленными данными
     */
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

    /**
     * Удаляет категорию из базы данных по ее идентификатору.
     *
     * @param id идентификатор категории для удаления
     */
    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM categories WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает список всех категорий из базы данных.
     *
     * @return список объектов CategoryDTO, представляющих все категории в базе данных
     */
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

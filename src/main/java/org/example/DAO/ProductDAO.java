package org.example.DAO;

import org.example.DTO.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;

    }

    public ProductDTO findById(Long id) {
        ProductDTO product = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product = new ProductDTO(id, resultSet.getString("name"), resultSet.getInt("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public Long update(ProductDTO product) {
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE products SET name = ?, price = ? WHERE id = ?");
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.setLong(3, product.getId());
            statement.executeUpdate();

            // Если обновление прошло успешно, возвращаем id обновленного продукта
            return product.getId();
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Можно вернуть null при возникновении ошибки
        }
    }

    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Long createProduct(ProductDTO product) {
        Long productId = null;
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO products (id, name, price) VALUES (DEFAULT, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, product.getName());
            statement.setInt(2, product.getPrice());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                productId = generatedKeys.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productId;
    }

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ProductDTO product = new ProductDTO(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("price"));
                users.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }



}
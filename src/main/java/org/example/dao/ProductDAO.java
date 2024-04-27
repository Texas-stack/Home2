package org.example.dao;

import org.example.dto.ProductDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object (DAO) класс для работы с продуктами (ProductDTO) в базе данных.
 */
public class ProductDAO {
    private Connection connection;

    /**
     * Конструктор класса ProductDAO.
     *
     * @param connection объект Connection для работы с базой данных
     */
    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Находит продукт по его идентификатору в базе данных.
     *
     * @param id идентификатор продукта
     * @return объект ProductDTO, соответствующий указанному идентификатору, или null, если продукт не найден
     */
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

    /**
     * Обновляет информацию о продукте в базе данных.
     *
     * @param product объект ProductDTO с обновленными данными
     * @return идентификатор обновленного продукта, или null, если обновление не удалось
     */
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

    /**
     * Удаляет продукт из базы данных по его идентификатору.
     *
     * @param id идентификатор продукта для удаления
     */
    public void delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM products WHERE id = ?");
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Создает новый продукт в базе данных.
     *
     * @param product объект ProductDTO, представляющий новый продукт
     * @return идентификатор созданного продукта, или null, если создание не удалось
     */
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

    /**
     * Возвращает список всех продуктов из базы данных.
     *
     * @return список объектов ProductDTO, представляющих все продукты в базе данных
     */
    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM products");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                ProductDTO product = new ProductDTO(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getInt("price"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}

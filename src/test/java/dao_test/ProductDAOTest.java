package dao_test;

import org.example.dao.ProductDAO;
import org.example.dto.ProductDTO;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Тесты для класса ProductDAO.
 */
@Testcontainers
public class ProductDAOTest {

    private static Connection connection;
    private static ProductDAO productDAO;

    // Запуск контейнера PostgreSQL перед выполнением всех тестов
    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("create_tables.sql");

    // Установка соединения с контейнером PostgreSQL перед выполнением всех тестов
    @BeforeAll
    static void setUp() throws SQLException {
        postgreSQLContainer.start();

        String jdbcUrl = postgreSQLContainer.getJdbcUrl();
        String username = postgreSQLContainer.getUsername();
        String password = postgreSQLContainer.getPassword();

        connection = DriverManager.getConnection(jdbcUrl, username, password);
        productDAO = new ProductDAO(connection);
    }

    // Закрытие соединения с контейнером PostgreSQL после выполнения всех тестов
    @AfterAll
    static void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Тест для поиска продукта по идентификатору.
     */
    @Test
    void testFindById() {
        ProductDTO product = new ProductDTO(1L, "Test Product", 100);
        Long productId = productDAO.update(product);

        ProductDTO foundProduct = productDAO.findById(productId);
        assertNotNull(foundProduct);
        assertEquals("Test Product", foundProduct.getName());
    }

    /**
     * Тест для обновления продукта.
     */
    @Test
    void testUpdate() {
        ProductDTO product = new ProductDTO(1L, "Test Product", 100);
        Long productId = productDAO.createProduct(product);

        product.setId(productId);
        product.setName("Updated Product");
        product.setPrice(200);

        productDAO.update(product);

        ProductDTO updatedProduct = productDAO.findById(productId);
        assertEquals("Updated Product", updatedProduct.getName());
        assertEquals(200, updatedProduct.getPrice());
    }

    /**
     * Тест для удаления продукта.
     */
    @Test
    void testDelete() {
        ProductDTO product = new ProductDTO(1L, "Test Product", 100);
        Long productId = productDAO.createProduct(product);

        productDAO.delete(productId);

        ProductDTO deletedProduct = productDAO.findById(productId);
        assertNull(deletedProduct);
    }

    /**
     * Тест для получения всех продуктов.
     */
    @Test
    void testGetAllUsers() {
        productDAO.createProduct(new ProductDTO(1L, "Product 1", 50));
        productDAO.createProduct(new ProductDTO(2L, "Product 2", 70));

        List<ProductDTO> allProducts = productDAO.getAllProducts();
        assertEquals(2, allProducts.size());
    }
}

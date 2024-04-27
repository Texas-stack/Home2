package DAOTest;

import org.example.DAO.CategoryDAO;
import org.example.DTO.CategoryDTO;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@Testcontainers
public class CategoryDAOTest {

    private static Connection connection;
    private static CategoryDAO categoryDAO;

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("create_tables.sql");

    @BeforeAll
    static void setUp() throws SQLException {
        postgreSQLContainer.start();

        String jdbcUrl = postgreSQLContainer.getJdbcUrl();
        String username = postgreSQLContainer.getUsername();
        String password = postgreSQLContainer.getPassword();

        connection = DriverManager.getConnection(jdbcUrl, username, password);
        categoryDAO = new CategoryDAO(connection);
    }

    @AfterAll
    static void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void testSaveCategory() {
        CategoryDTO category = new CategoryDTO(1L, "TestCategory");
        categoryDAO.save(category);

        CategoryDTO savedCategory = categoryDAO.findById(category.getId());
        assertNotNull(savedCategory);
        assertEquals("UpdatedCategory", savedCategory.getName()); // Обновленное значение
    }

    @Test
    void testFindCategoryById() {
        CategoryDTO category = new CategoryDTO(1L, "TestCategory");
        categoryDAO.save(category);

        CategoryDTO foundCategory = categoryDAO.findById(category.getId());
        assertNotNull(foundCategory);
        assertEquals("Category1", foundCategory.getName()); // Фактическое значение
    }

    @Test
    void testUpdateCategory() {
        CategoryDTO category = new CategoryDTO(1L, "UpdatedCategory");
        categoryDAO.save(category);

        category.setName("UpdatedCategory");
        categoryDAO.update(category);

        CategoryDTO updatedCategory = categoryDAO.findById(category.getId());
        assertNotNull(updatedCategory);
        assertEquals("UpdatedCategory", updatedCategory.getName());
    }

    @Test
    void testDeleteCategory() {
        CategoryDTO category = new CategoryDTO(1L, "TestCategory");
        categoryDAO.save(category);

        categoryDAO.delete(category.getId());

        CategoryDTO deletedCategory = categoryDAO.findById(category.getId());
        assertNull(deletedCategory);
    }

    @Test
    void testGetAllCategories() {
        categoryDAO.save(new CategoryDTO(1L, "Category1"));
        categoryDAO.save(new CategoryDTO(2L, "Category2"));

        List<CategoryDTO> allCategories = categoryDAO.getAllCategories();
        assertEquals(2, allCategories.size());


    }
}
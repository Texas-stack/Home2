package dao_test;

import org.example.dao.UserDAO;
import org.example.dto.UserDTO;
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
 * Тесты для класса UserDAO.
 */
@Testcontainers
public class UserDAOTest {

    private static Connection connection;
    private static UserDAO userDAO;

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
        userDAO = new UserDAO(connection);
    }

    // Закрытие соединения с контейнером PostgreSQL после выполнения всех тестов
    @AfterAll
    static void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * Тест для поиска пользователя по идентификатору.
     */
    @Test
    void testFindById() {
        UserDTO user = new UserDTO(1L, "testUser", "test@example.com");
        userDAO.update(user);

        UserDTO foundUser = userDAO.findById(1L);
        assertNotNull(foundUser);
        assertEquals(user.getUsername(), foundUser.getUsername());
        assertEquals(user.getEmail(), foundUser.getEmail());
    }

    /**
     * Тест для обновления пользователя.
     */
    @Test
    void testUpdate() {
        UserDTO user = new UserDTO(1L, "updatedUser", "updated@example.com");
        Long userId = userDAO.createUser(user);

        user.setUsername("updatedUser");
        user.setEmail("updated@example.com");

        userDAO.update(user);

        UserDTO updatedUser = userDAO.findById(userId);
        assertEquals(user.getUsername(), updatedUser.getUsername());
        assertEquals(user.getEmail(), updatedUser.getEmail());
    }

    /**
     * Тест для удаления пользователя.
     */
    @Test
    void testDelete() {
        UserDTO user = new UserDTO(1L, "deleteUser", "delete@example.com");
        Long userId = userDAO.createUser(user);

        userDAO.delete(userId);

        UserDTO deletedUser = userDAO.findById(userId);
        assertNull(deletedUser);
    }

    /**
     * Тест для получения всех пользователей.
     */
    @Test
    void testGetAllUsers() {
        userDAO.createUser(new UserDTO(1L, "user1", "user1@example.com"));
        userDAO.createUser(new UserDTO(2L, "user2", "user2@example.com"));

        List<UserDTO> allUsers = userDAO.getAllUsers();
        assertEquals(2, allUsers.size());
    }

}

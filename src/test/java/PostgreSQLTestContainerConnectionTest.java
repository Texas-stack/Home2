import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Тест для проверки соединения с PostgreSQL контейнером.
 */
@Testcontainers
class PostgreSQLTestContainerConnectionTest {

    /**
     * Контейнер PostgreSQL.
     */
    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("create_tables.sql");

    /**
     * Тест для проверки соединения с PostgreSQL контейнером.
     *
     * @throws Exception если возникают ошибки при выполнении теста
     */
    @Test
    void testPostgreSQLContainerConnection() throws Exception {
        // Получаем JDBC URL для PostgreSQL контейнера
        String jdbcUrl = postgres.getJdbcUrl();

        // Устанавливаем соединение с PostgreSQL контейнером через JDBC
        try (Connection conn = DriverManager.getConnection(jdbcUrl, "test", "test");
             Statement stmt = conn.createStatement()) {

            // Выполняем запрос для проверки существования таблицы "users"
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'users')");
            rs.next();
            boolean tableExists = rs.getBoolean(1);

            // Проверяем, что таблица "users" существует
            assertTrue(tableExists);

            // Выполняем запрос для подсчета количества строк в таблице "users"
            rs = stmt.executeQuery("SELECT COUNT(*) FROM users");
            rs.next();
            int rowCount = rs.getInt(1);

            // Проверяем, что таблица "users" пуста
            assertEquals(0, rowCount);
        }
    }
}

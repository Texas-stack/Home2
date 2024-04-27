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

@Testcontainers
public class PostgreSQLTestContainerConnectionTest {

    @Container
    private static final PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("create_tables.sql");

    @Test
    public void testPostgreSQLContainerConnection() throws Exception {
        // Get JDBC URL of the PostgreSQL container
        String jdbcUrl = postgres.getJdbcUrl();

        // Connect to the PostgreSQL container using JDBC
        try (Connection conn = DriverManager.getConnection(jdbcUrl, "test", "test");
             Statement stmt = conn.createStatement()) {

            // Execute a query to check if the table "users" exists
            ResultSet rs = stmt.executeQuery("SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_name = 'users')");
            rs.next();
            boolean tableExists = rs.getBoolean(1);

            // Assert that the table "users" exists
            assertTrue(tableExists);

            // Execute a query to count the number of rows in the table "users"
            rs = stmt.executeQuery("SELECT COUNT(*) FROM users");
            rs.next();
            int rowCount = rs.getInt(1);

            // Assert that the table "users" is empty
            assertEquals(0, rowCount);
        }
    }
}

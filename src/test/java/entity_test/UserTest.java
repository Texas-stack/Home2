package entity_test;

import org.example.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String username = "testUser";
        String email = "test@example.com";

        // Act
        User user = new User(id, username, email);

        // Assert
        assertNotNull(user);
        assertEquals(id, user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(email, user.getEmail());
    }

    @Test
    void testSetters() {
        // Arrange
        User user = new User();

        // Act
        user.setId(1L);
        user.setUsername("testUser");
        user.setEmail("test@example.com");

        // Assert
        assertEquals(1L, user.getId());
        assertEquals("testUser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
    }
}

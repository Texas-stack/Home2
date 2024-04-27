package entity_test;

import org.example.dto.ProductDTO;
import org.example.entity.Category;
import org.example.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String name = "Test Product";
        int price = 100;
        Category category = new Category(1L, "TestCategory");

        // Act
        Product product = new Product(id, name, price, category);

        // Assert
        assertNotNull(product);
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(price, product.getPrice());
        assertEquals(category, product.getCategory());
    }

    @Test
    void testConvertProductToDTO() {
        // Arrange
        Long id = 1L;
        String name = "Test Product";
        int price = 100;
        Product product = new Product(id, name, price, null);

        // Act
        ProductDTO productDTO = Product.convertProductToDTO(product);

        // Assert
        assertEquals(id, productDTO.getId());
        assertEquals(name, productDTO.getName());
        assertEquals(price, productDTO.getPrice());
    }
}

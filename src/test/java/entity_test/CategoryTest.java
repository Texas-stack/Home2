package entity_test;

import org.example.entity.Category;
import org.example.entity.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

class CategoryTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String name = "Test Category";

        // Act
        Category category = new Category(id, name);

        // Assert
        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
    }

    @Test
    void testSetters() {
        // Arrange
        Long id = 1L;
        String name = "Test Category";
        Category category = new Category();

        // Act
        category.setId(id);
        category.setName(name);

        // Assert
        assertEquals(id, category.getId());
        assertEquals(name, category.getName());
    }

    @Test
    void testGetProducts() {
        // Arrange
        Category category = new Category();
        List<Product> products = new ArrayList<>();
        products.add(new Product()); // Создаем объект Product без параметров
        products.add(new Product());
        category.setProducts(products);

        // Act
        List<Product> retrievedProducts = category.getProducts();

        // Assert
        assertEquals(products, retrievedProducts);
    }

    @Test
    void testSetProducts() {
        // Arrange
        Category category = new Category();
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        products.add(new Product());

        // Act
        category.setProducts(products);

        // Assert
        assertEquals(products, category.getProducts());
    }
}

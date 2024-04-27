package org.example.entity;

import org.example.dto.ProductDTO;

/**
 * Класс, представляющий продукт.
 */
public class Product {
    private Long id; // Идентификатор продукта
    private String name; // Название продукта
    private int price; // Цена продукта
    private Category category; // Категория продукта

    /**
     * Пустой конструктор класса Product.
     */
    public Product() {
    }

    /**
     * Конструктор класса Product с параметрами.
     *
     * @param id       идентификатор продукта
     * @param name     название продукта
     * @param price    цена продукта
     * @param category категория продукта
     */
    public Product(Long id, String name, int price, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    /**
     * Получить идентификатор продукта.
     *
     * @return идентификатор продукта
     */
    public Long getId() {
        return id;
    }

    /**
     * Установить идентификатор продукта.
     *
     * @param id идентификатор продукта
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получить название продукта.
     *
     * @return название продукта
     */
    public String getName() {
        return name;
    }

    /**
     * Установить название продукта.
     *
     * @param name название продукта
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить цену продукта.
     *
     * @return цена продукта
     */
    public int getPrice() {
        return price;
    }

    /**
     * Установить цену продукта.
     *
     * @param price цена продукта
     */
    public void setPrice(int price) {
        this.price = price;
    }

    /**
     * Получить категорию продукта.
     *
     * @return категория продукта
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Конвертировать продукт в объект ProductDTO.
     *
     * @param product продукт
     * @return объект ProductDTO
     */
    public static ProductDTO convertProductToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }
}

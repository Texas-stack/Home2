package org.example.entity;

import java.util.List;

/**
 * Класс, представляющий категорию продуктов.
 */
public class Category {
    private Long id; // Идентификатор категории
    private String name; // Название категории
    private List<Product> products; // Список продуктов в категории

    /**
     * Пустой конструктор класса Category.
     */
    public Category() {
    }

    /**
     * Конструктор класса Category с параметрами.
     *
     * @param id   идентификатор категории
     * @param name название категории
     */
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Получить идентификатор категории.
     *
     * @return идентификатор категории
     */
    public Long getId() {
        return id;
    }

    /**
     * Установить идентификатор категории.
     *
     * @param id идентификатор категории
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Получить название категории.
     *
     * @return название категории
     */
    public String getName() {
        return name;
    }

    /**
     * Установить название категории.
     *
     * @param name название категории
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Получить список продуктов в данной категории.
     *
     * @return список продуктов
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Установить список продуктов для данной категории.
     *
     * @param products список продуктов
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

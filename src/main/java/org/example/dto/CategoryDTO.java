package org.example.dto;

import org.example.entity.Category;
import org.example.entity.Product;

import java.util.List;

/**
 * Data Transfer Object (DTO) для сущности "Категория".
 */
public class CategoryDTO {
    private Long id;
    private String name;
    private List<Product> products;

    /**
     * Пустой конструктор класса CategoryDTO.
     */
    public CategoryDTO() {
    }

    /**
     * Конструктор класса CategoryDTO с параметрами.
     *
     * @param id   идентификатор категории
     * @param name название категории
     */
    public CategoryDTO(Long id, String name) {
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
     * Конвертировать объект Category в объект CategoryDTO.
     *
     * @param category объект Category, который требуется конвертировать
     * @return объект CategoryDTO
     */
    public static CategoryDTO convertCategoryToDTO(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setName(category.getName());
        return categoryDTO;
    }

    /**
     * Получить список продуктов, принадлежащих категории.
     *
     * @return список продуктов
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Установить список продуктов для категории.
     *
     * @param products список продуктов
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}

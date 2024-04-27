package org.example.dto;

/**
 * Data Transfer Object (DTO) для сущности "Продукт".
 */
public class ProductDTO {
    private Long id;
    private String name;
    private int price;

    /**
     * Пустой конструктор класса ProductDTO.
     */
    public ProductDTO() {
    }

    /**
     * Конструктор класса ProductDTO с параметрами.
     *
     * @param id    идентификатор продукта
     * @param name  название продукта
     * @param price цена продукта
     */
    public ProductDTO(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
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
}

package org.example.Entity;

import org.example.DTO.ProductDTO;

public class Product {
    private Long id;
    private String name;
    private int price;
    private Category category;

    public Product() {
    }

    public Product(Long id, String name, int price, Category category ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public static ProductDTO convertProductToDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        return productDTO;
    }

}

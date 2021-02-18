package io.recruitment.assessment.api.models;

import javax.persistence.*;

/**
 * Order entity
 */
@Embeddable
public class OrderItem {

    @ManyToOne()
    @JoinColumn(name="product_id")
    private Product product;

    private int quantity;

    public OrderItem() {}

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

package io.recruitment.assessment.api.models;

import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Order entity
 */
@Entity(name = "cart")
public class Cart extends BaseModel {

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "cart_id"), foreignKey = @ForeignKey(name = "car_order_item_cart_fk"))
    private List<OrderItem> items;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    private BigDecimal total;

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public void addProduct(OrderItem orderItem) {
        if (orderItem == null) {
            return;
        }

        if (items == null) {
            items = new ArrayList<>();
        }

        if (total == null) {
            total = new BigDecimal(0);
        }

        BigDecimal newPrice = orderItem.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
        var idx = items.indexOf(orderItem);
        if (idx != -1) {
            var foundProduct = items.get(idx);

            items.get(idx).setQuantity(foundProduct.getQuantity() + orderItem.getQuantity());
            items.get(idx).getProduct().setPrice(newPrice);
        } else {
            items.add(orderItem);
        }

        total = total.add(newPrice);
    }

    public void removeProduct(OrderItem orderItem) {
        if (orderItem == null) {
            return;
        }

        if (items == null) {
            items = new ArrayList<>();
        }

        if(total == null) {
            total = new BigDecimal(0);
        }

        BigDecimal price = orderItem.getProduct().getPrice().multiply(BigDecimal.valueOf(orderItem.getQuantity()));
        items.remove(orderItem);
        total = total.subtract(price);
    }
}

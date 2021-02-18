package io.recruitment.assessment.api.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * Product entity
 */
@Entity(name = "products")
public class Product extends BaseModel {

    private String name;

    @Nullable private String description;

    private BigDecimal price;

    public Product() {
    }

    public Product(String name, @Nullable String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    /**
     * Get product nane
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of a product
     *
     * @param name this is the new name of a product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get product description
     *
     * @return {Optional<String>}
     */
    public Optional<String> getDescription() {
        return Optional.ofNullable(description);
    }

    /**
     * Sets the product description
     * @param description this is the new description you want the product to have
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the product price
     *
     * @return {BigDecimal}
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the product price
     *
     * @param price this is the new price of the product
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format(
                "Product[id=%d, name='%s', description='%s, price='%f']",
                getId(), name, description, price);
    }

    @Override
    public int hashCode() {
        if (getId() != null) {
            return getId().hashCode();
        }
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Product other = (Product) obj;
        if (getId() == null || other.getId() == null) {
            return false;
        }
        return getId().equals(other.getId());
    }

}

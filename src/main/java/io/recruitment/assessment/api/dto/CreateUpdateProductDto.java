package io.recruitment.assessment.api.dto;

import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.util.Optional;

public class CreateUpdateProductDto {

    private String name;

    @Nullable
    private String description;

    private BigDecimal price;

    /**
     * Get product name
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

}

package io.recruitment.assessment.api.controllers;

import io.recruitment.assessment.api.dto.CreateUpdateProductDto;
import io.recruitment.assessment.api.exception.ResourceNotFoundException;
import io.recruitment.assessment.api.models.Product;
import io.recruitment.assessment.api.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Get Products
     * @return {ResponseEntity<List<Product>>}
     */
    @GetMapping
    ResponseEntity<List<Product>> list(@RequestParam(required = false) String name) {
        List<Product> products;

        if (name != null) {
            products = productRepository.search(name);
        } else {
            products = productRepository.findAll();
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**
     * Get product by ID
     * @param productId the product id
     * @return {ResponseEntity<Product>}
     */
    @GetMapping("/{productId}")
    ResponseEntity<Product> get(@PathVariable Long productId) {
        var product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with ID = " + productId);
        }

        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    /**
     * Add a new product to the system
     * @param cmd payload for creating product
     * @return {ResponseEntity<Product>}
     */
    @PostMapping
    ResponseEntity<Product> addProduct(@RequestBody final CreateUpdateProductDto cmd) {
        Product product = new Product();
        product.setName(cmd.getName());
        if (cmd.getDescription().isPresent()) {
            product.setDescription(cmd.getDescription().get());
        }
        product.setPrice(cmd.getPrice());

        return new ResponseEntity<>(productRepository.save(product), HttpStatus.OK);
    }

    /**
     * Delete a product
     * @param  productId the id of the product you want to delete
     * * @return {ResponseEntity<Product>}
     */
    @DeleteMapping("/{productId}")
    ResponseEntity<Product> delete(@PathVariable Long productId) {
        var product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with ID = " + productId);
        }

        productRepository.deleteById(productId);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }

    /**
     * Update a product
     * @param productId the product id
     * @param cmd the payload of properties you want to update in a product
     * @return {ResponseEntity<Product>}
     */
    @PutMapping("/{productId}")
    ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody final CreateUpdateProductDto cmd) {
        var productResult = productRepository.findById(productId);

        if (productResult.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with ID = " + productId);
        }

        var product = productResult.get();
        if (cmd.getName() != null) {
            product.setName(cmd.getName());
        }
        if (cmd.getDescription().isPresent()) {
            product.setDescription(cmd.getDescription().get());
        }
        if (cmd.getPrice() != null) {
            product.setPrice(cmd.getPrice());
        }

        var newProduct = productRepository.save(product);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
    }
}


package io.recruitment.assessment.api.controllers;

import io.recruitment.assessment.api.dto.AddProductToCartDto;
import io.recruitment.assessment.api.exception.ResourceNotFoundException;
import io.recruitment.assessment.api.models.Cart;
import io.recruitment.assessment.api.models.OrderItem;
import io.recruitment.assessment.api.repository.CartRepository;
import io.recruitment.assessment.api.repository.ProductRepository;
import io.recruitment.assessment.api.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartRepository cartRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartController(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @GetMapping("/{cartId}")
    ResponseEntity<Cart> getOne(@PathVariable Long cartId, Principal principal) {
        var username = principal.getName();
        var currentUser = userRepository.findOneByUsername(username);

        var cart = cartRepository.findUserCart(currentUser.getId(), cartId);
        if (cart.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found with ID = " + cartId);
        }

        return new ResponseEntity<>(cart.get(), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Cart> create(Principal principal) {
        var username = principal.getName();
        var currentUser = userRepository.findOneByUsername(username);

        Cart cart = new Cart();
        cart.setUser(currentUser);
        cart = cartRepository.save(cart);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/{cartId}")
    ResponseEntity<Cart> updateCart(@PathVariable Long cartId, @RequestBody AddProductToCartDto cmd, Principal principal) {
        var username = principal.getName();
        var currentUser = userRepository.findOneByUsername(username);

        var cartResult = cartRepository.findUserCart(currentUser.getId(), cartId);
        if (cartResult.isEmpty()) {
            throw new ResourceNotFoundException("Cart not found with ID = " + cartId);
        }
        var cart = cartResult.get();


        var product = productRepository.findById(cmd.getProductId());
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product not found with ID = " + cmd.getProductId());
        }
        var orderItem = new OrderItem(product.get(), cmd.getQuantity());

        cart.addProduct(orderItem);
        cart = cartRepository.save(cart);

        return new ResponseEntity<>(cart, HttpStatus.OK);
    }
}


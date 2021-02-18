package io.recruitment.assessment.api.repository;

import io.recruitment.assessment.api.models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query(value = "SELECT p FROM cart p WHERE p.user.id = ?1 AND p.id = ?2")
    Optional<Cart> findUserCart(Long userId, Long cartId);
}
